package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class TubeTest {
    /**
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


    }
