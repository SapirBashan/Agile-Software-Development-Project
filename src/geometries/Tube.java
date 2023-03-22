package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
/**
 * The Tube class represents a tube in a three-dimensional space.
 * A tube is defined by a center axis ray and a radius value.
 */
public class Tube extends RadialGeometry {
    /** The axis ray of the tube. */
    protected Ray axisRay;
    /**
     * Constructs a new Tube object with the specified radius.
     * @param radius the radius value of the tube
     */
    public Tube(double radios) {
        super(radios);
    }
    /**
     * Returns the normal vector to the tube at the specified point.
     * @param p a point on the tube (unused in this implementation)
     * @return the normal vector to the tube
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
