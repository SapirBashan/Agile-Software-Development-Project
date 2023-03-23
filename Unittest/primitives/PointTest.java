package primitives;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {


    @org.junit.jupiter.api.Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============

    // TC01: two different points
    Point p1 = new Point(1, 2, 3);
    Vector p2 =  new Vector(2, 3, 4);
    assertEquals(new Point(3, 5, 7), p1.add(p2), "ERROR: Point + Vector does not work correctly");

    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void distanceSquared() {
    }

    @org.junit.jupiter.api.Test
    void distance() {
    }
}