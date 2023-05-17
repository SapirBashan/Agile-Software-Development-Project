package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase{

    /**
     * The delta for the shadow rays.
     */
    private static final double DELTA = 0.1;



    /**
     * Constructor for RayTracerBase class.
     * @param scene The scene.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersectionsHelper(ray);
        if (intersections == null) return scene.background;
        Intersectable.GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }

    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
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
                return false;
        }
        return true;
    }

    private Double3 calcDiffusive(Material material , double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v){
        Vector r = n.scale(2*nl).subtract(l);
        return material.kS.scale(Math.pow(Math.max(0.0, (v.dotProduct(r))), material.nShininess));
    }

}
