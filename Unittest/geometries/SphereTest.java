package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SphereTest {

    @Test
    void testGetNormal() {
        Point o = new Point(0, 0, 0);
        Sphere s = new Sphere(1, o);
        // ============ Equivalence Partitions Tests ==============

        // TC01: normal to sphere
        Vector vn = new Vector(1, 0, 0).normalize();
        assertTrue((s.getNormal(new Point(1, 0, 0)).equals(vn)), "ERROR: getNormal() wrong value");


        //TC02 : if the vector is normal
        assertEquals(0, (s.getNormal(new Point(1,0,0)).length() -1), 0.000001, "ERROR: the vector was not normal");

    }
}