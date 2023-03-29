package geometries;

import primitives.*;

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
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        // check that the three points are not the same
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("ERROR: two points are the same");

         // check that the three points are not on the same line
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p2);
        try {
            v2.crossProduct(v1);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: three points are on the same line");
        }

       q0 = p1;
       normal = (v1.crossProduct(v2)).normalize();

    }

    /**
     * Constructs a plane from a point on the plane and a normal vector to the plane.
     * @param p0 a point on the plane
     * @param vNormal the normal vector to the plane
     */
    public Plane(Point p0, Vector vNormal){
        q0 = p0;
        normal = vNormal.normalize();
    }

    /**
     * Returns the normal vector to the plane at the specified point.
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
}
