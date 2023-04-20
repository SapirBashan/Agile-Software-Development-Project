package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SphereTest {

    /**
     * tests the function getNormal of the sphere
     * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
     * @throws IllegalArgumentException
     */
    @Test
    void testGetNormal() {
        Point o = new Point(0, 0, 0);
        Sphere s = new Sphere(1, o);
        // ============ Equivalence Partitions Tests ==============

        // TC01: normal to sphere
        Vector vn = new Vector(1, 0, 0).normalize();
        assertEquals(vn, s.getNormal(new Point(1, 0, 0)), "ERROR: getNormal() wrong value");


        //TC02 : if the vector is normal
        assertEquals(0, (s.getNormal(new Point(1,0,0)).length() -1), 0.000001, "ERROR: the vector was not normal");
    }

    /**
     * tests the function FindIntersections of the sphere
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     * @throws IllegalArgumentException
     */
    @Test
    public void testFindIntersections() {
        Point o = new Point(1,0,0);
        Sphere sphere = new Sphere(1, o);
        List<Point> result;
        Vector v;
        Point p, p1, p2;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        p = new Point(1, 0.5, 0);
        v = new Vector(-1, 0.5,0);
        Ray r = new Ray(p, v);
        result = sphere.findIntersections(r);

        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0.4, 0.8,0)), result, "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        r = new Ray(new Point(0,1,0), v);
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        v = new Vector(-1,0.5,0);
        r = new Ray(new Point(0.4, 0.8, 0), v.scale(-1.0));
        result = sphere.findIntersections(r);

        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2,0,0)), result, "Ray crosses sphere");

        // TC12: Ray starts at sphere and goes outside (0 points)
        r = new Ray(new Point(0.4, 0.8, 0), v);
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        p = new Point(-1,0,0);
        p1 = new Point(0,0,0);
        p2 = new Point(2,0,0);
        v = new Vector(1,0,0);

        // TC13: Ray starts before the sphere (2 points)
        r = new Ray(p, v);
        result = sphere.findIntersections(r);
        assertEquals(2, result.size(), "Wrong number of points");

        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 point)
        r = new Ray(p1, v);
        result = sphere.findIntersections(r);
        assertEquals(1, result.size(), "Wrong number of points");

        assertEquals(List.of(p2), result, "Ray crosses sphere");

        // TC15: Ray starts inside (1 point)
        Point p3 = new Point(0.5,0,0);
        r = new Ray(p3, v);
        result = sphere.findIntersections(r);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p2), result , "Ray crosses sphere");

        // TC16: Ray starts at the center (1 point)
        r = new Ray(sphere.getCenter(), v);
        result = sphere.findIntersections(r);

        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p2), result, "Ray crosses sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        r = new Ray(new Point(0,0,0), v.scale(-1.0));
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // TC18: Ray starts after sphere (0 points)
        r = new Ray(new Point(3,0,0), v);
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        r = new Ray(new Point(2,1,0), v.scale(-1.0));
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // TC20: Ray starts at the tangent point
        r = new Ray(new Point(1,1,0), v.scale(-1.0));
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // TC21: Ray starts after the tangent point
        r = new Ray(new Point(2,1,0), v);
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        r = new Ray(new Point(3,0,0), new Vector(0,1,0));
        assertNull(sphere.findIntersections(r), "Ray's line out of sphere");
    }
}