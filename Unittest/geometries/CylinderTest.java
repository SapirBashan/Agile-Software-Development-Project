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
            Ray ray = new Ray(new Point(2,0,0), new Vector(0,0,1));
            Cylinder cylinder = new Cylinder(1, ray, 2);

            // ============ Equivalence Partitions Tests ==============

            //TC01 ray is outside and parallel to the cylinder's ray
            List<Point> result = cylinder.findIntersections(new Ray(new Point(5,0,0), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

            //TC02 ray starts inside and parallel to the cylinder's ray
            result = cylinder.findIntersections(new Ray(new Point(2.5,0,1), new Vector(0,0,1)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(2.5,0,2)), result, "Bad intersection point");

            //TC03 ray starts outside and parallel to the cylinder's ray and crosses the cylinder
            result = cylinder.findIntersections(new Ray(new Point(2.5,0,-1), new Vector(0,0,1)));
            assertEquals(2, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(2.5, 0, 0), new Point(2.5,0,2)), result, "Bad intersection point");

            //TC04 ray starts from outside and crosses the cylinder
            result = cylinder.findIntersections(new Ray(new Point(-2,0,0), new Vector(1,0,0)));
            //assertEquals(2, result.size(), "Wrong number of points");
            //assertEquals(List.of(new Point3D(1,0,0), new Point3D(3,0,0)), result, "Bad intersection points");
            assertNull(result, "Wrong number of points");

            //TC05 ray starts from inside and crosses the cylinder
            result = cylinder.findIntersections(new Ray(new Point(1.5,0,0.5), new Vector(1,0,0)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,0.5)), result, "Bad intersection points");

            //TC06 ray starts from outside the cylinder and doesn't cross the cylinder
            result = cylinder.findIntersections(new Ray(new Point(5,0,0), new Vector(1,0,0)));
            assertNull(result, "Wrong number of points");

            // =============== Boundary Values Tests ==================

            //TC07 ray is on the surface of the cylinder (not bases)
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

            //TC08 ray is on the base of the cylinder and crosses 2 times
            result = cylinder.findIntersections(new Ray(new Point(-1,0,0), new Vector(1,0,0)));
            assertNull(result, "Wrong number of points");
            //assertEquals(List.of(new Point3D(1,0,0), new Point3D(3,0,0)), result, "Bad intersection points");

            //TC08 ray is in center of the cylinder
            result = cylinder.findIntersections(new Ray(new Point(2,0,0), new Vector(0,0,1)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(2,0,2)), result, "Bad intersection points");

            //TC09 ray is perpendicular to cylinder's ray and starts from outside the tube
            result = cylinder.findIntersections(new Ray(new Point(-2,0,0.5), new Vector(1,0,0)));
            assertEquals(2, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(1,0,0.5), new Point(3,0,0.5)), result, "Bad intersection points");

            //TC09 ray is perpendicular to cylinder's ray and starts from inside cylinder (not center)
            result = cylinder.findIntersections(new Ray(new Point(1.5,0,0.5), new Vector(1,0,0)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,0.5)), result, "Bad intersection points");

            //TC10 ray is perpendicular to cylinder's ray and starts from the center of cylinder
            result = cylinder.findIntersections(new Ray(new Point(2,0,0.5), new Vector(1,0,0)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,0.5)), result, "Bad intersection points");

            //TC11 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to inside
            result = cylinder.findIntersections(new Ray(new Point(1,0,0.5), new Vector(1,0,0)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,0.5)), result, "Bad intersection points");

            //TC12 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to outside
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,0,0)));
            assertNull(result, "Wrong number of points");

            //TC13 ray starts from the surface to outside
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,1,1)));
            assertNull(result, "Wrong number of points");

            //TC14 ray starts from the surface to inside
            result = cylinder.findIntersections(new Ray(new Point(3,0,-0.5), new Vector(-1,0,1)));
            //assertEquals(1, result.size(), "Wrong number of points");
            //assertEquals(List.of(new Point3D(1,0,2)), result, "Bad intersection point");

            //TC15 ray starts from the center
            result = cylinder.findIntersections(new Ray(new Point(2,0,0), new Vector(1,0,1)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,1)), result, "Bad intersection point");

            //TC16 prolongation of ray crosses cylinder
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,0,0)));
            assertNull(result, "Wrong number of points");

            //TC17 ray starts from the surface to outside
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,1,1)));
            assertNull(result, "Wrong number of points");

            //TC18 ray starts from the surface to inside
            result = cylinder.findIntersections(new Ray(new Point(3,0,0.5), new Vector(-1,0,0)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(1,0,0.5)), result, "Bad intersection point");

            //TC19 ray starts from the center
            result = cylinder.findIntersections(new Ray(new Point(2,0,0), new Vector(1,0,1)));
            assertEquals(1, result.size(), "Wrong number of points");
            assertEquals(List.of(new Point(3,0,1)), result, "Bad intersection point");

            //TC20 prolongation of ray crosses cylinder
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,0,0)));
            assertNull(result, "Wrong number of points");

            //TC21 ray is on the surface starts before cylinder
            result = cylinder.findIntersections(new Ray(new Point(3,0,-1), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

            //TC22 ray is on the surface starts at bottom's base
            result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

            //TC23 ray is on the surface starts on the surface
            result = cylinder.findIntersections(new Ray(new Point(3,0,1), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

            //TC24 ray is on the surface starts at top's base
            result = cylinder.findIntersections(new Ray(new Point(3,0,2), new Vector(0,0,1)));
            assertNull(result, "Wrong number of points");

        }



}