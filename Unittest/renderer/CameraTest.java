package renderer;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;

class CameraTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    @Test
    void testCamera() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: 2 vectors that are not orthogonal
        Vector vTo = new Vector(1, 2, 3);
        Vector vUp = new Vector(2, 4, 6);
        assertThrows(IllegalArgumentException.class, () -> new Camera(null, vTo, vUp) , "vTo and vUp must be orthogonal");


        // TC02: 2 vectors that are orthogonal
        Vector vTo1 = new Vector(1, 1, 0);
        Vector vUp1 = new Vector(1, -1, 0);
        Camera camera = new Camera(null, vTo1, vUp1);
        assertEquals(new Vector(0, 0, -2).normalize() , camera.getvRight(), "vTo and vUp must be orthogonal");

    }
    @Test
    void testConstructRay() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
        String badRay = "Bad ray";

        // ============ Equivalence Partitions Tests ==============
        // EP01: 4X4 Inside (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 1), badRay);

        // =============== Boundary Values Tests ==================
        // BV01: 3X3 Center (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);

        // BV02: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 0), badRay);

        // BV03: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 1), badRay);

        // BV04: 3X3 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 0), badRay);

        // BV05: 4X4 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 0, 0), badRay);

        // BV06: 4X4 Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 0), badRay);

    }
}