package primitives;

import java.util.Objects;

public class Point {
    protected Double3 xyz;

    public Point(double x, double y, double z){
        this.xyz = new Double3(x, y, z);
    }

    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    public Point add(Vector v){
        Double3 newXYZ = this.xyz.add(v.xyz);
        return new Point(newXYZ);
    }

    public Vector subtract(Point p){
        Double3 newXYZ = this.xyz.subtract(p.xyz);
        return new Vector(newXYZ);
    }

    public double distanceSquared(Point p){
        double dX = (p.xyz.d1 - this.xyz.d1);
        double dY = (p.xyz.d2 - this.xyz.d2);
        double dZ = (p.xyz.d3 - this.xyz.d3);

        return (dX * dX) + (dY * dY) + (dZ * dZ);
    }

    public double distance(Point p){
        return Math.sqrt(this.distanceSquared(p));
    }

}
