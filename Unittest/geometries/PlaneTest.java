package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Point p1 = new Point(1,0,0);
        Point p2 = new Point(0,1,0);
        Point p3 = new Point(0,0,1);
        Plane plane = new Plane(p1,p2,p3);

        Point p = new Point(0,0,0);
        Ray r;
        Vector v;
        List<Point> result;
        // ============ Equivalence Partitions Tests ==============

        // TC 00: ray start before the plane, not parallel and not orthogonal
        v = new Vector(1,0,0);
        r = new Ray(p, v);
        result = plane.findIntsersections(r);

        assertEquals(1, result.size());
        assertEquals(List.of(new Point(1,0,0)), result);

        // TC 01: ray start after the plane, not parallel and not orthogonal
        v = new Vector(1,0,0);
        r = new Ray(p, v.scale(-1.0));

        assertNull(plane.findIntsersections(r));

        // =============== Boundary Values Tests =================
        // TC 10: the ray start at the plane
        v = new Vector(1,0,0);
        r = new Ray(p1, v);

        assertNull(plane.findIntsersections(r));

        // TC 11: the ray parallel to the plane
        v = new Vector(1,0,0);
        r = new Ray(p, v.scale(-1.0));

        assertNull(plane.findIntsersections(r));

        // TC 12: the ray orthogonal to the plane
        v = new Vector(1,1,1);
        r = new Ray(p, v);
        result = plane.findIntsersections(r);

        assertEquals(1, result.size());
        assertEquals(List.of(new Point(1.0/3, 1.0/3, 1.0/3)), result);
    }
}