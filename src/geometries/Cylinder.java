package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a cylinder in 3D space.
 */
public class Cylinder extends Tube{
    private double height;
    /**
     * Constructs a new Cylinder object with the given radius and height.
     *
     * @param radios The radius of the cylinder.
     * @param height The height of the cylinder.
     */
    public Cylinder(double radios, double height) {
        super(radios);
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
