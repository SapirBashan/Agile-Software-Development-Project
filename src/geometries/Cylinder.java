package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    /**
     * The height of the cylinder.
     */
    private final double height;
    /**
     * Constructs a new Cylinder object with the specified radius, axis ray and height.
     * @param radius the radius value of the cylinder
     * @param axisRay the axis ray of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

/**
     * Returns the height of the cylinder.
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector to the cylinder at the specified point.
     * @param p a point on the cylinder (unused in this implementation)
     * @return the normal vector to the cylinder
     */
    public Vector getNormal(Point p) {
        return null;
    }

}
