package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

class CylinderTest {

    /**
     * tests the constructor of Cylinder
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        Point o = new Point(0, 0, 0);
        Vector v = new Vector(0, 1, 0);
        Cylinder c = new Cylinder(2, new Ray(o,v), 4);
        // ============ Equivalence Partitions Tests ==============
        // TC01:  on the round surface
        Vector vn1 = new Vector(1, 0, 0).normalize();
        assertTrue(vn1.equals(c.getNormal(new Point(2, 3, 0))) || vn1.equals(c.getNormal(new Point(-2,-3,0)))
                , "ERROR: getNormal() TC01 the point is not on the round surface");

        //TC02: on the base height of the cylinder
        Vector vn2 = new Vector(0, 0, 0.5).normalize();
        assertTrue(vn2.equals(c.getNormal(new Point(0, 0, 1))) || vn2.equals(c.getNormal(new Point(0,0,-1)))
                , "ERROR: getNormal() TC02 the point is not on the base height of the cylinder");

        //TC03: on the base height equal to max height in this case it is 4
        Vector vn3 = new Vector(0, 0, 0.5).normalize();
        assertTrue(vn3.equals(c.getNormal(new Point(0, 0, 4))) || vn3.equals(c.getNormal(new Point(0,0,-4)))
                , "ERROR: getNormal() TC03 the point is not on the base height of the cylinder");


        // =============== Boundary Values Tests ==================
        // TC10: the case of the point in the center of the cylinder with height equal to 0
        Vector vn4 = new Vector(0, 1, 0).normalize();
        assertTrue(vn4.equals(c.getNormal(new Point(0, 0, 0))) || vn4.equals(c.getNormal(new Point(0,0,0)))
                , "ERROR: getNormal() TC10 the point is not on the base height of the cylinder");

        //TC11: the case of the point in the center of the cylinder with height equal to his max height
        Vector vn5 = new Vector(0, 1, 0).normalize();
        assertTrue(vn5.equals(c.getNormal(new Point(0, 4, 0))) || vn5.equals(c.getNormal(new Point(0,-4,0)))
                , "ERROR: getNormal() TC11 the point is not on the base height of the cylinder");

    }
    /**
     * tests the constructor of Cylinder
     * Test method for {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Point o = new Point(2, 0, 0);
        Vector v = new Vector(0, 0, 1);
        Cylinder cylinder = new Cylinder(2, new Ray(o,v), 1);

        // ============ Equivalence Partitions Tests ==============

        //TC01 ray is outside and parallel to the cylinder's ray
        List<Point> result = cylinder.findIntersections(new Ray(new Point(5,0,0), new Vector(0,0,1)));
        assertNull(result, "ERROR: findIntersections() TC01 ray is outside and parallel to the cylinder's ray");

        //TC02 ray starts inside and parallel to the cylinder's ray
        result = cylinder.findIntersections(new Ray(new Point(3,0,0.5), new Vector(3,0,1)));
        assertEquals(1, result.size(), "Wrong number of points");
        //assertEquals(List.of(new Point(3,0,1)), result, "ERROR: the point is in the wrong place");

        //TC03 ray starts outside and parallel to the cylinder's ray and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point(2.5,0,-1), new Vector(0,0,1)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertTrue(result.get(0).equals(new Point(2.5,0,0)) || result.get(0).equals(new Point(2.5,0,1)), "ERROR: the point is in the wrong place");

        //TC04 ray starts from outside and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point(-2,0,0), new Vector(1,0,0)));
        assertNull(result, "Wrong number of points");

        //TC05 ray starts from inside and crosses the cylinder
        result = cylinder.findIntersections(new Ray(new Point(1.5,0,0.5), new Vector(1,0,0)));
        assertEquals(1, result.size(), "Wrong number of points");
        //assertEquals(List.of(new Point(3,0,0.5)), result, "Bad intersection points");

        //TC06 ray starts from outside the cylinder and doesn't cross the cylinder
        result = cylinder.findIntersections(new Ray(new Point(5,0,0), new Vector(1,0,0)));
        assertNull(result, "Wrong number of points");
    }



}