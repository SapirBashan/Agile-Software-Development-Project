package primitives;

import java.util.Objects;
/**
 * The Point class represents a point in a three-dimensional space.
 * A point is defined by its x, y, and z coordinates.
 */
public class Point {
    /**
     * The Point class represents a point in a three-dimensional space.
     * A point is defined by its x, y, and z coordinates.
     */
    protected Double3 xyz;
    /**
     * Constructs a new Point object with the specified x, y, and z coordinates.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     */
    public Point(double x, double y, double z){
        this.xyz = new Double3(x, y, z);
    }
    /**
     * Constructs a new Point object with the specified Double3 object.
     * @param xyz the Double3 object representing the x, y, and z coordinates of the point
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }
    /**
     * Returns true if this point is equal to the specified object.
     * Two points are equal if their x, y, and z coordinates are equal.
     * @param o the object to compare with this point
     * @return true if this point is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }
    /**
     * Returns a string representation of this point.
     * The string representation is "Point{x,y,z}", where x, y, and z are the x, y, and z coordinates of the point.
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
    /**
     * Returns a new Point object that represents the sum of this point and the specified vector.
     * @param v The vector to add to this point.
     * @return A new Point object representing the sum of this point and the specified vector.
     */
    public Point add(Vector v){
        Double3 newXYZ = this.xyz.add(v.xyz);
        return new Point(newXYZ);
    }
    /**
     * Returns a new Vector object that represents the difference between this point and the specified point.
     * @param p The point to subtract from this point.
     * @return A new Vector object representing the difference between this point and the specified point.
     */
    public Vector subtract(Point p){
        Double3 newXYZ = this.xyz.subtract(p.xyz);
        return new Vector(newXYZ);
    }
    /**
     * Returns the distance squared of this point and a different given point .
     * @param p The point to find the distance squared from this point.
     * @return A new Vector object representing the distance squared between this point and the specified point.
     */
    public double distanceSquared(Point p){
        double dX = (p.xyz.d1 - this.xyz.d1);
        double dY = (p.xyz.d2 - this.xyz.d2);
        double dZ = (p.xyz.d3 - this.xyz.d3);

        return (dX * dX) + (dY * dY) + (dZ * dZ);
    }
    /**
     * Returns the distance of this point and a different given point .
     * @param  p The point to find the distance from this point.
     * @return The reel distance between p and this point
     **/
    public double distance(Point p){
        return Math.sqrt(this.distanceSquared(p));
    }

}
