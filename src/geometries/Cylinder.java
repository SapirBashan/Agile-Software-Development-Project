package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;


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
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }
    /**
     * Returns the normal of the cylinder at the specified point.
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

    /**
     * Returns a list of the intersections of the cylinder with the specified ray.
     * @param ray The ray to find the intersections with.
     * @return A list of the intersections of the cylinder with the specified ray.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        //P1 and P2 in the cylinder, the center of the bottom and upper bases
        Point p1 = axisRay.getP0();
        Point p2 = axisRay.getTargetPoint(height);
        Vector Va = axisRay.getDir();

        List<Point> list = super.findIntersections(ray);

        //the intersections with the cylinder
        List<Point> result = new LinkedList<>();

        //Step 1 - checking if the intersections with the tube are points on the cylinder
        if (list != null) {
            for (Point p : list) {
                if (Va.dotProduct(p.subtract(p1)) > 0 && Va.dotProduct(p.subtract(p2)) < 0)
                    result.add(0, p);
            }
        }

        //Step 2 - checking the intersections with the bases

        //cannot be more than 2 intersections
        if(result.size() < 2) {
            //creating 2 planes for the 2 bases
            Plane bottomBase = new Plane(p1, Va);
            Plane upperBase = new Plane(p2, Va);
            Point p;

            // ======================================================
            // intersection with the bases:

            //intersections with the bottom bases
            list = bottomBase.findIntersections(ray);

            if (list != null) {
                p = list.get(0);
                //checking if the intersection is on the cylinder base
                if (p.distanceSquared(p1) < radius * radius)
                    result.add(p);
            }

            //intersections with the upper bases
            list = upperBase.findIntersections(ray);

            if (list != null) {
                p = list.get(0);
                //checking if the intersection is on the cylinder base
                if (p.distanceSquared(p2) < radius * radius)
                    result.add(p);
            }
        }
        //return null if there are no intersections.
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }    }


}
