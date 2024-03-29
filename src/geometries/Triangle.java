package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        //check if the ray intersect with the plane of the triangle
        List<GeoPoint> result = plane.findGeoIntersectionsHelper(ray);
        if(result == null)
           return null;
        Point p = result.get(0).point;
        //check if the point is inside the triangle
        //if the point is on the same side of the triangle's edges, it is inside the triangle
        //if the point is on the edge of the triangle, it is inside the triangle
        //if the point is on the opposite side of the triangle's edges, it is outside the triangle
        try {
            Vector n1 = (vertices.get(1).subtract(vertices.get(0))).crossProduct(vertices.get(0).subtract(p));
            Vector n2 = (vertices.get(2).subtract(vertices.get(1))).crossProduct(vertices.get(1).subtract(p));
            Vector n3 = (vertices.get(0).subtract(vertices.get(2))).crossProduct(vertices.get(2).subtract(p));

            if(n1.dotProduct(n2) < 0 || n1.dotProduct(n3) < 0 || n2.dotProduct(n3) < 0){
                return null;
            }
        }
        catch (IllegalArgumentException e){
            return null;
        }

        return List.of(new GeoPoint(this,p));
    }
}
