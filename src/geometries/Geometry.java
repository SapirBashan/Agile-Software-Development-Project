package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;



public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the normal of the geometry at the specified point.
     *
     * @param p The point to calculate the normal at.
     * @return The normal of the geometry at the specified point.
     */
    public abstract Vector getNormal(Point p);

}
