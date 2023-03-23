package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;


/**
 * Represents a cylinder in 3D space.
 */
public class Cylinder extends Tube{
    private final double height;
    /**
     * Constructs a new Cylinder object with the given radius and height.
     *
     * @param radius The radius of the cylinder.
     * @param height The height of the cylinder.
     */
    public Cylinder(double radius, Ray AxisRay, double height) {
        super(radius, AxisRay);
        this.height = height;
    }
    /**
     * Returns the height of the cylinder.
     *
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }
  /**
         * Returns the normal of the cylinder at the specified point.
         *
         * @param p The point to calculate the normal at.
         * @return The normal of the cylinder at the specified point.
         */
    public Vector getNormal(Point p) {
        return null;
    }

}
