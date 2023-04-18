package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaneTest {

    @Test
    void testPlane() {

        // =============== Boundary Values Tests ==================
        // TC10: two points are the same
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(1, 2, 3);
        Point p3 = new Point(2, 3, 4);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3), "ERROR: two points are the same");

        //TC11: three points are on the same line
        Point p4 = new Point(1, 1, 1);
        Point p5 = new Point(1,1,2);
        Point p6 = new Point(1,1,3);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p4, p5, p6), "ERROR: three points are on the same line");
        }

        /**
         * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
         */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: normal to plane
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 3, 4);
        Point p3 = new Point(3, 4, 6);
        Plane plane = new Plane(p1, p2, p3);
        Vector vn = (new Vector(1,-1,0)).normalize();
        assertTrue((plane.getNormal().equals(vn) || plane.getNormal().equals(vn.scale(-1.0))), "ERROR: getNormal() wrong value");

        //TC02 : if the vector is normal
        assertEquals(0, (plane.getNormal().length() -1), 0.000001, "ERROR: the vector was not normal");
    }

    @Test
    void testfindIntsersections(){
        // ============ Equivalence Partitions Tests ==============


    }
}