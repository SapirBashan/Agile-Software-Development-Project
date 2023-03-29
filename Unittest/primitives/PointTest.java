package primitives;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointTest {

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @org.junit.jupiter.api.Test
    public void testAdd() {
        Point p = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: two different points
        Vector v =  new Vector(2, 3, 4);
        assertEquals(new Point(3, 5, 7), p.add(v), "ERROR: Point + Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC10: result is zero
        assertEquals(new Point(0,0,0), p.add(new Vector(-1, -2, -3)));
    }
    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @org.junit.jupiter.api.Test
    void testSubtract() {
        Point p1 = new Point(3, 4, 5);

        // ============ Equivalence Partitions Tests ==============

        // TC01: two different points
        Point p2 =  new Point(2, 3, 4);
        assertEquals(new Vector(1, 1, 1), p1.subtract(p2), "ERROR: Point - Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC10: result is zero
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "ERROR: Point - Vector does not work correctly");
    }
     /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @org.junit.jupiter.api.Test
    void distanceSquared() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: two different points
        Point p1 = new Point(1, 2, 3);
        Point p2 =  new Point(2, 3, 4);
        assertEquals(3, p1.distanceSquared(p2), "ERROR: Point distanceSquared does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @org.junit.jupiter.api.Test
    void distance() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: two different points
        Point p1 = new Point(1, 2, 3);
        Point p2 =  new Point(2, 3, 4);
        assertEquals(Math.sqrt(3), p1.distance(p2), "ERROR: Point distance does not work correctly");
    }
}