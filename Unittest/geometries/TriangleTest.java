package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(t.getNormal(p1).length() == 1, "ERROR: the vector was not normal");
    }
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray is in the tiangle
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Triangle t = new Triangle(p1, p2, p3);
        Ray ray = new Ray(new Point(0, 0.5, 0), new Vector(0.5, 0, 0.5));
        assertEquals(1, t.findIntsersections(ray).size(), "ERROR: Ray does not intersect the triangle");

        //TC02: Ray is not in the triangle
        ray = new Ray(new Point(0, -1, 0), new Vector(2, 1,0.5 ));
        assertEquals(null, t.findIntsersections(ray), "ERROR: Ray intersects the triangle");

        //TC03: Ray is in the plane but not in the triangle
        ray = new Ray(new Point(-1, 0, 0), new Vector(3, -1, 2));
        assertEquals(null, t.findIntsersections(ray), "ERROR: Ray intersects the triangle");

        // =============== Boundary Values Tests ==================
        //TC10: Ray hits the point of the triangle
        ray = new Ray(new Point(-1, 0, 0), new Vector(2, 0, 0));
        assertEquals(1, t.findIntsersections(ray).size(), "ERROR: Ray does not intersect the triangle");

        //TC11: Ray hits the edge of the triangle
        ray = new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0));
        assertEquals(1, t.findIntsersections(ray).size(), "ERROR: Ray does not intersect the triangle");

        //TC12: Ray hits the continuation of the edge of the triangle
        ray = new Ray(new Point(-1, 0, 0), new Vector(1, -0.5, 1.5));
        assertEquals(null, t.findIntsersections(ray), "ERROR: Ray intersects the triangle");






    }
}

