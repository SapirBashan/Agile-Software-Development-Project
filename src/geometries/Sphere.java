package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a sphere in a three-dimensional space.
 * A sphere is defined by a center point and a radius value.
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere. */
    private Point center;
    /** The radius value of the sphere. */
    private double radios;

    /**
     * Constructs a new Sphere object with the specified radius and center point.
     * @param radius the radius value of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radios, Point center, double radios1) {
        super(radios);
        this.center = center;
        this.radios = radios1;
    }
    /**
     * Returns the center point of the sphere.
     * @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }
    /**
     * Returns the radius value of the sphere.
     * @return the radius value of the sphere
     */
    public double getRadios() {
        return radios;
    }
    /**
     * Returns the normal vector to the sphere at the specified point.
     * @param p a point on the sphere (unused in this implementation)
     * @return the normal vector to the sphere
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
