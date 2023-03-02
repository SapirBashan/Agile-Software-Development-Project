package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Tube extends RadialGeometry {
    protected Ray axisRay;
    public Tube(double radios) {
        super(radios);
    }
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
