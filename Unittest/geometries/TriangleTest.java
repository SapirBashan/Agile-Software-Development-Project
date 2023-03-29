package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}