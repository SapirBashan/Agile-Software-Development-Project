package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube{


    final private double height;

    public Cylinder(double radios, double height) {
        super(radios);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public Vector getNormal(Point p) {
        return null;
    }

}
