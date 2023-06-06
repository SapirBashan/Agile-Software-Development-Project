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
     * The maximum level for the recursive calculation of the color.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The minimum k for the recursive calculation of the color.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * The delta for the shadow rays.
     */
    private int amountOfRays = 1;

    /**
     * Sets the amount of rays.
     * @param amountOfRays The amount of rays.
     * @return The RayTracerBasic object.
     */
    public RayTracerBasic setAmountOfRays(int amountOfRays) {
        this.amountOfRays = amountOfRays;
        return this;
    }

    /**
     * Constructor for RayTracerBase class.
     * @param scene The scene.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        //this calculation is for the formula of the closest point
        Intersectable.GeoPoint closestPoint = findClosestIntersection(ray);
        //if there is no intersection point
        //return the background color
        //else return the color of the point
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color of the point.
     *
     * @param closestPoint The point.
     * @param ray The ray.
     * @return The color of the point.
     */
    private Color calcColor(Intersectable.GeoPoint closestPoint, Ray ray) {
        RayBeam beam = new RayBeam(ray,closestPoint.point,amountOfRays);
        List<Ray> rays = beam.getBeam();

        Color color = Color.BLACK;

        for (Ray r: rays){
            color = color.add(calcColor(closestPoint, r, MAX_CALC_COLOR_LEVEL, INITIAL_K));
        }

        return color.scale(1.0/rays.size());

        //this calculation is for the formula of the color
        //return the color of the point
        //return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color of the point.
     *
     * @param intersection The point.
     * @param ray The ray.
     * @param level The level of the recursive calculation.
     * @param k The k for the recursive calculation.
     * @return The color of the point.
     */
    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray, int level, Double3 k) {
        //this is a recursive function that calculates the color of the point
        //if the level is 0 or the k is smaller than the minimum k
        //return the black color
        //else calculate the local effects and the global effects
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the color of the point.
     *
     * @param gp The point.
     * @param ray The ray.
     * @return The color of the point.
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray, Double3 k) {
        //this calculation is for the formula of the color
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        //if the sign of the normal and the sign of the vector are the same
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        //this for loop is for the calculation of the color of the point
        //with the help of the light sources and the transparency of the geometry
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            //if the sign of the normal and the sign of the vector are the same
            //and the sign of the normal and the sign of the light source are the same
            //and the point is not shaded by another geometry
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)){
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }

        return color;
    }

    /**
     * calculates the transparency of the light going through thr object.
     *
     * @param ls The material of the geometry.
     * @param gp The dot product of the normal and the light source.
     * @return The diffusive of the point.
     */
    private Double3 transparency(Intersectable.GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1.0); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        Double3 ktr = Double3.ONE;
        //if there is no intersection point
        //return the ktr else calculate the ktr
        if (intersections == null) return ktr;
        //this for loop is for the calculation of the ktr of the point
        for(Intersectable.GeoPoint geoPoint : intersections){
            //if the point is shaded by another geometry
            if (alignZero(geoPoint.point.distance(gp.point) - ls.getDistance(gp.point)) <= 0){
                ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
            }
        }

        return ktr;
    }


    /**
     * calculates the global effects of the point with the help of the
     *
     * @param level The material of the geometry.
     * @param gp The dot product of the normal and the light source.
     * @return The diffusive of the point.
     */
    private Color calcGlobalEffects(Intersectable.GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        //if the level is 0 or the k is smaller than the minimum k
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        //if the transparency of the geometry is 0
        return calcGlobalEffects(constructReflectedRay(gp, v, n),level, k, material.kR)//
         .add(calcGlobalEffects(constructRefractedRay(gp, v, n),level, k, material.kT));
    }

    /**
     * calculates the global effects of the point with the help of the
     * reflected ray and the refracted ray
     *
     * @param level The material of the geometry.
     * @param ray The dot product of the normal and the light source.
     * @return the color of the point.
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        Intersectable.GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);

        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK :
                calcColor(gp, ray, level-1, kkx).scale(kx);
    }


    /**
     * Calculates the diffusive of the point.
     *
     * @param material The material of the geometry.
     * @param nl The dot product of the normal and the light source.
     * @return The diffusive of the point.
     */
    private Double3 calcDiffusive(Material material , double nl) {
        //acording to model of phong
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calculates the specular of the point
     *
     * @param material The material of the geometry.
     * @param nl The dot product of the normal and the light source.
     * @return the specular of the point.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v){
        //acording to model of phong
        Vector r = n.scale(2*nl).subtract(l);
        return material.kS.scale(Math.pow(Math.max(0.0, (v.dotProduct(r))), material.nShininess));
    }

    /**
     * construct the reflected ray from the point
     *
     * @param gp The material of the geometry.
     * @param v The dot product of the normal and the light source.
     * @return the reflected ray .
     */
    private Ray constructReflectedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(gp.point, r, n);
    }


    /**
     * construct the refracted ray from the point
     *
     * @param gp The material of the geometry.
     * @param v The dot product of the normal and the light source.
     * @return the refracted ray .
     */
    private Ray constructRefractedRay(Intersectable.GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
    }

    /**
     * find the closest intersection point to the ray
     *
     * @param ray The material of the geometry.
     * @return the closest intersection point to the ray.
     */
    private Intersectable.GeoPoint findClosestIntersection(Ray ray){
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

}
