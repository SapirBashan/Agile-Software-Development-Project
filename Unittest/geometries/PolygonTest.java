package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertNull;
import static primitives.Util.isZero;

class PolygonTests {

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1),
                    new Point(1, 0, 0),
                    new Point(0, 1, 0),
                    new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(0, 1, 0),
                        new Point(1, 0, 0),
                        new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    @Test
    void testFindIntersections() {
        List<Point> result;
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(-1, 0, 0);
        Point p3 = new Point(-1, 0, 1);
        Point p4 = new Point(1,0,1);
        Polygon pol = new Polygon(p1, p2, p3, p4);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray is in the polygon
        Ray r = new Ray(new Point(0,-1,0.5), new Vector(0,1,0));
        result = pol.findIntersections(r);
        assertEquals(1, result.size(), "ERROR: Ray does not intersect the triangle");
        assertEquals(List.of(new Point( 0,0,0.5)), result);

        //TC02: Ray is not in the polygon
        r = new Ray(new Point(0, -1, 0.5), new Vector(0, -1,0 ));
        assertNull(pol.findIntersections(r), "ERROR: Ray intersects the triangle");

        //TC03: Ray is in the plane but not in the polygon
        r = new Ray(new Point(4, -1, 0.5), new Vector(0, 1, 0));
        assertNull(pol.findIntersections(r), "ERROR: Ray intersects the triangle");

        // =============== Boundary Values Tests ==================
        //TC10: Ray hits the point of the polygon
        r = new Ray(new Point(-1, -1, 0), new Vector(0, 1, 0));
        assertNull(pol.findIntersections(r), "ERROR: Ray does not intersect the triangle");

        //TC11: Ray hits the edge of the polygon
        r = new Ray(new Point(0, -1, 0), new Vector(0, 1, 0));
        assertNull(pol.findIntersections(r), "ERROR: Ray does not intersect the triangle");

        //TC12: Ray hits the continuation of the edge of the polygon
        r = new Ray(new Point(4, -1, 0), new Vector(0, 1, 0));
        assertNull(pol.findIntersections(r), "ERROR: Ray intersects the triangle");
    }

}