package geometries;

import primitives.Point;

/**
 * The Triangle class represents a triangle in a three-dimensional space.
 * A triangle is defined by three non-collinear points in space.
 */
public class Triangle extends Polygon{
    /**
     * Constructs a new Triangle object with the specified three non-collinear points.
     * @param p1 the first point of the triangle
     * @param p2 the second point of the triangle
     * @param p3 the third point of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }
}
