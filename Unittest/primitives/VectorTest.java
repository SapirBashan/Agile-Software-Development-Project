package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void testConstructor(){
        //TC00 : zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0,0,0), "ERROR: zero vector does not throw an exception");
    }
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: two different points that are not orthogonal
        Vector v2 = new Vector(1, 4, 5);
        assertEquals(new Vector(2, 6, 8), v1.add(v2), "ERROR: Point + Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC10: result is zero
        assertThrows(IllegalArgumentException.class, () -> v1.add(v1.scale(-1.0)), "ERROR: Point + Vector does not work correctly");

    }

    @Test
    void testScale() {
        Vector v = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: multiple by non zero number
        assertEquals(new Vector(2, 4, 6), v.scale(2.0), "ERROR: Double * Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC10: result is zero
        assertThrows(IllegalArgumentException.class, () -> v.scale(0.0), "ERROR: Double * Vector does not work correctly");
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: two different vectors that are not parallel
        Vector v2 = new Vector(2, 3, 4);
        assertEquals(new Vector(-1, 2, -1), v1.crossProduct(v2), "ERROR: crossProduct() for two different vectors does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC10: two vectors are parallel
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1.scale(2.0)), "ERROR: Double * Vector does not work correctly");
    }

    @Test
    void lengthSquared() {
       //this function is based on dot product, and we already tested dot product
    }

    @Test
    void length() {
        //this function is based on dot product, and we already tested dot product
    }


    @Test
    void normalize() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: normalize a vector
        Vector v = new Vector(1, 2, 3);
        Vector Normal = v.normalize();
        assertEquals(0, Normal.length() -1 , "ERROR: normalize() function creates a vector that is not normal to itself");
    }


    @Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: two different points that are not orthogonal
        Vector v2 = new Vector(2, 3, 4);
        assertEquals(20, v1.dotProduct(v2), "ERROR: dotProduct() for two different vectors does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC10: two vectors are orthogonal
        assertEquals(0, v1.dotProduct(new Vector(2,-1,0)), "ERROR: dotProduct() for two orthogonal vectors does not work correctly");
    }
}