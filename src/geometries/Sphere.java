package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{

    final private Point center;
    final private double radios;

    public Sphere(double radios, Point center, double radios1) {
        super(radios);
        this.center = center;
        this.radios = radios1;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadios() {
        return radios;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
