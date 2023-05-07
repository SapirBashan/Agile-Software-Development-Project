package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;



public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
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
