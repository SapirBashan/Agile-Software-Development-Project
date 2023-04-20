package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertNotNull;

class TubeTest {
    /**
     * test the getNormal function of the tube
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: normal to tube
        Point p1 = new Point(0, 0, 0);
        Vector v = new Vector(1, 0, 0);
        Tube t = new Tube(1, new Ray(p1, v));
        Vector tn = new Vector(0, 1, 0).normalize();
        assertEquals(tn, t.getNormal(new Point(1, 1, 0)), "ERROR: getNormal() wrong value");

        //TC02 : if the vector is normal
        assertEquals(0, (t.getNormal(new Point(1,1,0)).length() -1), 0.000001, "ERROR: the vector was not normal");

        // =============== Boundary Values Tests ==================
        // TC10: the point minus p0(o) is orthogonal to the ray
        assertThrows(IllegalArgumentException.class, () -> t.getNormal(new Point(0, 1, 0)) , "ERROR: the point minus p0(o) is orthogonal to the ray");
        }

        @Test
        void findIntersections() {

            Point p1 = new Point(1, 1, 1);
            Vector v1 = new Vector(0, 1, 0);
            Tube tube1 = new Tube(1, new Ray(p1, v1));
            Point p2 = new Point(1, 1, -7);
            Vector v2 = new Vector(0, 0, 1);
            Tube tube2 = new Tube(1, new Ray(p2, v2));

            Ray ray;
            // ============ Equivalence Partitions Tests ==============
            // TC01: Ray's line is outside the tube (0 points)
            ray = new Ray(new Point(1, 1, 2), new Vector(1, 1, 0));
            assertEquals(null, tube1.findIntersections(ray), "ERROR: Ray's line out of tube");

            // TC02: Ray's crosses the tube (2 points)
            // TC02: Ray's crosses the tube (2 points)
            ray = new Ray(new Point(0, 0, 0), new Vector(2, 1, 1));
            List<Point> result = tube2.findIntersections(ray);
            assertNotNull(result, "ERROR - TC02: must be intersections");
            assertEquals(2, result.size(), "ERROR - TC02: must be 2 intersections");
            if (result.get(0).getY() > result.get(1).getY())
                result = List.of(result.get(1), result.get(0));
            assertEquals(
                    List.of(new Point(0.4, 0.2, 0.2),
                            new Point(2, 1, 1)), result,
                    "ERROR - TC02: Bad intersections");

            // TC03: Ray's starts within tube and crosses the tube (1 point)
            ray = new Ray(new Point(1, 0.5, 0.5), new Vector(2, 1, 1));
            result = tube2.findIntersections(ray);
            assertNotNull(result, "ERROR - TC03: must be intersections");
            assertEquals(1, result.size(), "ERROR - TC03: must be 1 intersections");
            assertEquals(List.of(new Point(2, 1, 1)), result, "ERROR - TC03: Bad intersections");


        }

        }
