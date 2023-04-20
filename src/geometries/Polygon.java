package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon implements Geometry {

    /** List of polygon's vertices */
    protected final List<Point> vertices;

    /** Associated plane in which the polygon lays */
    protected final Plane plane;

    private final int size;

    /** Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     * @param  vertices                 list of vertices according to their order by
     *                                  edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size          = vertices.length;

        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane         = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    @Override
    public Vector getNormal(Point point) { return plane.getNormal(); }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public List<Point> findIntersections(Ray ray){
        List<Point> result = plane.findIntersections(ray);

        if(result == null)
            return null;

        Point p = result.get(0);
        List<Vector> vecs = new LinkedList<>();

        // check if the point is inside the polygon
        // we need a try for a case that the point one of the polygon's vertices
        try {
            Point prevP = vertices.get(vertices.size() - 1);  //the last point in the list
            for (Point p1 : vertices) {
                //if the point is on the edge of the polygon
                //the dot product will be zero
                vecs.add(p1.subtract(prevP).crossProduct(prevP.subtract(p)));
                prevP = p1;
            }

            Vector prevV = vecs.get(vecs.size()-1); //the last vector in the list
            for (Vector v : vecs) {
                if(v.dotProduct(prevV) < 0){
                    return null;
                }
                prevV = v;
            }
        }
        catch (IllegalArgumentException e){
            return null;
        }

        return result;
    }
}