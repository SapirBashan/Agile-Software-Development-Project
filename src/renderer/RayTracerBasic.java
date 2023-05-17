package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracerBase{


    private static final Double3 INITIAL_K = new Double3(1.0);
    /**
     * The delta for the shadow rays.
     */
    private static final double DELTA = 0.1;
    /**
     * The maximum level for the recursive calculation of the color.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The minimum k for the recursive calculation of the color.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Constructor for RayTracerBase class.
     * @param scene The scene.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Intersectable.GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the color of the point.
     *
     * @param gp The point.
     * @param ray The ray.
     * @return The color of the point.
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv;
        try {
            nv = alignZero(n.dotProduct(v));
        }
        catch (IllegalArgumentException e) {
            nv = 0;
        }
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if(unshaded(gp, lightSource, l, n,nl)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }

        return color;
    }

    /**
     * Checks if the point is shaded.
     *
     * @param gp The point to check.
     * @param l The light source.
     * @param n The normal.
     * @return true if the point is shaded, false otherwise.
     */
    private boolean unshaded(Intersectable.GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1.0); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersectionsHelper(lightRay);
        if (intersections == null) return true;

        for (Intersectable.GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightSource.getDistance(gp.point)) <= 0)
                if(gp.geometry.getMaterial().kT == Double3.ZERO)
                    return false;
        }
        return true;
    }

    private Color calcGlobalEffects(Intersectable.GeoPoint gp, Ray ray,
                                    int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcColorGlobalEffect(constructReflectedRay(gp, v, n),level, k, material.kR)//
         .add(calcColorGlobalEffect(constructRefractedRay(gp, v, n),level, k, material.kT));
    }

    private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        Intersectable.GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);
        Color color;
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK : calcColor(gp, ray, level-1, kkx);
    }


    private Double3 calcDiffusive(Material material , double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v){
        Vector r = n.scale(2*nl).subtract(l);
        return material.kS.scale(Math.pow(Math.max(0.0, (v.dotProduct(r))), material.nShininess));
    }

    private Ray constructReflectedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        Vector epsVector = n.scale(n.dotProduct(v) < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        return new Ray(point, r);
    }


    private Ray constructRefractedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        Vector epsVector = n.scale(n.dotProduct(v) < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        return new Ray(point, v);
    }
    private Intersectable.GeoPoint findClosestIntersection(Ray ray){
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray));
    }

}
