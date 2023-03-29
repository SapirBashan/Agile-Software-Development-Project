package geometries;

import primitives.Point;
import primitives.Vector;

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
}