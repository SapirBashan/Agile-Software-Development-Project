package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertNull;

/**
 * Testing Triangle
 */
class TriangleTest {
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: normal to triangle
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Triangle t = new Triangle(p1, p2, p3);
        Vector tn = new Vector(0.5, 0.5, 0.5).normalize();
        assertTrue((t.getNormal(new Point(0.5, 0.5, 0.5)).equals(tn)) ||
                (t.getNormal(new Point(0.5, 0.5, 0.5)).equals(tn.scale(-1.0))), "ERROR: getNormal() wrong value");
        //TC02 : if the vector is normal
        assertEquals(1, t.getNormal(p1).length(), "ERROR: the vector was not normal");
    }
    @Test
    void testFindIntersections() {
        List<Point> result;
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Triangle t = new Triangle(p1, p2, p3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray is in the triangle
        Ray r = new Ray(new Point(0,0,0), new Vector(1,1,1));
        result = t.findIntersections(r);
        assertEquals(1, result.size(), "ERROR: Ray does not intersect the triangle");
        assertEquals(List.of(new Point(1.0/3,1.0/3,1.0/3)), result);

        //TC02: Ray is not in the triangle
        r = new Ray(new Point(0, 0, 0), new Vector(-1, -1,-1 ));
        assertNull(t.findIntersections(r), "ERROR: Ray intersects the triangle");

        //TC03: Ray is in the plane but not in the triangle
        r = new Ray(new Point(0, -1, 0), new Vector(1, 0, 0));
        assertNull(t.findIntersections(r), "ERROR: Ray intersects the triangle");

        // =============== Boundary Values Tests ==================
        //TC10: Ray hits the point of the triangle
        r = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        assertNull(t.findIntersections(r), "ERROR: Ray does not intersect the triangle");

        //TC11: Ray hits the edge of the triangle
        r = new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0));
        assertNull(t.findIntersections(r), "ERROR: Ray does not intersect the triangle");

        //TC12: Ray hits the continuation of the edge of the triangle
        r = new Ray(new Point(-1, 0, 0), new Vector(1, -0.5, 1.5));
        assertNull(t.findIntersections(r), "ERROR: Ray intersects the triangle");

    }
}

