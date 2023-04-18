package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a plane in a three-dimensional space.
 * A plane is defined by a point on the plane and a normal vector
 * perpendicular to the plane.
 */
public class Plane implements Geometry{
    /** The point on the plane. */
    private final Point q0;

    /** The normal vector to the plane. */
    private final Vector normal;

    /**
     * Returns the point on the plane.
     * @return the point on the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * Constructs a plane from three points that lie on the plane.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        // check that the three points are not the same
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("ERROR: two points are the same");

        // check that the three points are not on the same line
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p2);
        try {
            v2.crossProduct(v1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR: three points are on the same line");
        }

        q0 = p1;
        normal = (v1.crossProduct(v2)).normalize();

    }

    /**
     * Constructs a plane from a point on the plane and a normal vector to the plane.
     *
     * @param p0      a point on the plane
     * @param vNormal the normal vector to the plane
     */
    public Plane(Point p0, Vector vNormal) {
        q0 = p0;
        normal = vNormal.normalize();
    }

    /**
     * Returns the normal vector to the plane at the specified point.
     *
     * @param p a point on the plane (unused in this implementation)
     * @return the normal vector to the plane
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * Returns the normal vector to the plane.
     * @return the normal vector to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public List<Point> findIntsersections(Ray ray) {
        double nv, t;
        try {
            nv = this.normal.dotProduct(ray.getDir());
            if (isZero(nv)) {
                return null;
            }
            t = alignZero(normal.dotProduct(q0.subtract(ray.getP0())) / nv);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if(t > 0) {
            Point p = ray.getP0().add(ray.getDir().scale(t));
            return List.of(p);
        }

        return null;

    }
}
