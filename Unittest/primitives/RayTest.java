package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        //=============================
        Ray r = new Ray(new Point(0,0,0), new Vector(1,0,0));
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(2,2,2);
        Point p3 = new Point(3,3,3);

        List<Point> l = List.of(p2,p1,p2);
        assertEquals(p1, r.findClosestPoint(l));

        l = List.of();
        assertNull(r.findClosestPoint(l));

        l = List.of(p1,p3,p2);
        assertEquals(p1, r.findClosestPoint(l));

        l = List.of(p2,p3,p1);
        assertEquals(p1, r.findClosestPoint(l));
    }
}