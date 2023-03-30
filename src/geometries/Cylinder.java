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

        double t;
        //if the point is on the edge of the cylinder return the direction of the axis ray
        //this try function checks if the point is on the edge of the cylinder
        if (p.equals(this.getAxisRay().getP0()) || p.equals(this.getAxisRay().getP0().add(this.getAxisRay().getDir().scale(this.height)))) {
            return this.getAxisRay().getDir().normalize();
        }
        //if the point is not on the edge of the cylinder
        else {
            try {
                t = this.getAxisRay().getDir().dotProduct(p.subtract(this.getAxisRay().getP0()));
            } catch (IllegalArgumentException e) {
                return this.getAxisRay().getDir().normalize();
            }
            //if the point is on the side of the cylinder
            if (t > 0 && t < this.height) {
                Point o = this.getAxisRay().getP0().add(this.getAxisRay().getDir().scale(t));
                return super.getNormal(p);
            }
            //if the point is not on the side of the cylinder
            else {
                return (p.subtract(this.getAxisRay().getP0())).normalize();
            }
        }
    }

}
