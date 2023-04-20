package geometries;
import primitives.Vector;
import primitives.Point;



public interface Geometry extends Intersectable {
    /**
     * Returns the normal of the geometry at the specified point.
     *
     * @param p The point to calculate the normal at.
     * @return The normal of the geometry at the specified point.
     */
    public Vector getNormal(Point p);

}
