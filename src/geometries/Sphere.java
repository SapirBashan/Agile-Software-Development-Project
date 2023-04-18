package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Sphere class represents a sphere in a three-dimensional space.
 * A sphere is defined by a center point and a radius value.
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere. */
    private final Point center;


    /**
     * Constructs a new Sphere object with the specified radius and center point.
     * @param radius the radius value of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     * @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }


    /**
     * Returns the normal vector to the sphere at the specified point.
     * @param p a point on the sphere (unused in this implementation)
     * @return the normal vector to the sphere
     */
    @Override
    public Vector getNormal(Point p) {
        return (p.subtract(center)).normalize();
    }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public List<Point> findIntsersections(Ray ray) {
        Point p1 = null;
        Point p2 = null;
        List<Point> list = null;
        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d > radios)
            return null;
        double th = Math.sqrt(radios * radios - d * d);
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 > 0 ) {
            p1 = ray.getP0().add(ray.getDir().scale(t1));
        }
        if (t2 > 0) {
            p2 = ray.getP0().add(ray.getDir().scale(t2));
        }
        if(p1 != null && p2 != null) {
             list = List.of(p1, p2);
        } else if (p1 != null) {
             list = List.of(p1);
        } else if (p2 != null) {
             list = List.of(p2);
        }
        return list;
    }
}