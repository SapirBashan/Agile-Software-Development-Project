package primitives;

import geometries.Intersectable.GeoPoint;
import renderer.Blackboard;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in 3D space, consisting of an origin point and a direction vector.
 */
public class Ray {

    /**
     * The delta for the shadow rays.
     */
    private static final double DELTA = 0.1;

    /**
     * The origin point of the ray.
     */
    private final Point p0;

    /**
     * The direction vector of the ray, normalized to unit length.
     */
    private final Vector dir;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    /**
     * Constructs a new Ray object with the given origin point and direction vector.
     *
     * @param p0 the origin point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

     /**
     * Constructs a new Ray object with the given origin point and direction vector.
     * @param p the origin point of the ray
     * @param dir the direction vector of the ray
     * @param n the normal vector of the geometry
     */
    public Ray(Point p, Vector dir, Vector n){
        Vector epsVector = n.scale(n.dotProduct(dir) > 0 ? DELTA : -DELTA);
        Point p0 = p.add(epsVector);
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Refactoring must be performed for the calculation code of a point on a ray:
     * P = p0 + t∙v.
     * Used wherever required in the implementations of findIntersections function.
     * @param t The distance to be calculated for the ray from its head
     * @return The 3D-point on the ray that is at a distance of t from the head of the ray
     */
    public Point getTargetPoint(double t) {
        if (!isZero(alignZero(t))) {
            return getP0().add(dir.scale(alignZero(t)));
        }
        return getP0();
    }



    /**
     * Checks if this Ray object is equal to another object.
     * @param o the object to compare this Ray object to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * Returns a string representation of this ray.
     * @return a string representation of this ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
    /**
     * Returns the hashcode of this ray.
     * @return the hashcode of this ray
     */
    public Point getPoint(double t){
        try {
            return this.p0.add(this.dir.scale(t));
        }catch (Exception e){
            return this.p0;
        }
    }

    /**
     * Returns the hashcode of this ray.
     * @return the hashcode of this ray
     */
    public List<Ray> rayBeam(Blackboard tarArea){

        List<Ray> rays = new LinkedList<>();

        for(Point p : tarArea.getPoints()){
            rays.add(new Ray(this.p0, p.subtract(this.p0)));
        }

        return rays;
    }

    /**
     * Returns the hashcode of this ray.
     * @return the hashcode of this ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /**
     * Returns the hashcode of this ray.
     * @return the hashcode of this ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections){
        if(intersections == null || intersections.isEmpty())
            return null;

        GeoPoint closestPoint = intersections.get(0);
        double d, minDis = p0.distance(closestPoint.point);
        for (GeoPoint gp : intersections) {
            d = p0.distance(gp.point);
            if (d < minDis) {
                closestPoint = gp;
                minDis = d;
            }
        }

        return closestPoint;
    }
}