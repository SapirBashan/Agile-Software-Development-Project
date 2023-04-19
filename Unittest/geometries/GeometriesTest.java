package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

class GeometriesTest {

    @Test
    void findIntersections() {
        Point p = new Point(0,0,0);
        Point p1 = new Point(4,0,0);
        Point p2 = new Point(0,4,0);
        Point p3 = new Point(0,0,4);
        Plane plain = new Plane(p1,p2,p3);
        Triangle triangle = new Triangle(new Point(-1,0,0),new Point(0,-1,0), new Point(0,0,-1));
        Sphere sphere = new Sphere(1, p1);
        Ray ray;
        Geometries geometries;

        // ============ Equivalence Partitions Tests ==============
        // TC 00: some geometries are intersectable
        geometries = new Geometries(plain, triangle, sphere);
        ray = new Ray(new Point(-4,0,0), new Vector(1,0,0));
        assertEquals(3, geometries.findIntersections(ray).size());

        // =============== Boundary Values Tests =================
        // TC 10: empty geometries list
        geometries = new Geometries();
        ray = new Ray(p, new Vector(1,1,1));
        assertNull(geometries.findIntersections(ray));

        // TC 11: no geometry are intersectable
        geometries = new Geometries(plain, triangle, sphere);
        ray = new Ray(new Point(-4,-4,-4), new Vector(-1,0,0));
        assertNull(geometries.findIntersections(ray));

        // TC 12: one geometry are intersectable
        geometries = new Geometries(plain, triangle, sphere);
        ray = new Ray(p, new Vector(0,1,0));
        assertEquals(1, geometries.findIntersections(ray).size());

        // TC 13: all geometries are intersectable
        geometries = new Geometries(plain, triangle, sphere);
        ray = new Ray(new Point(-1,-1,-1), new Vector(2,1,1));
        assertEquals(1, geometries.findIntersections(ray).size());
    }
}