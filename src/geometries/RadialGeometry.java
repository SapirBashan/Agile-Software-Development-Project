package geometries;

/**
 * The RadialGeometry abstract class represents a geometry with a radial characteristic,
 * such as a sphere or cylinder, that is defined by a radius value.
 */
abstract class RadialGeometry extends Geometry{

    /** The radius value of the geometry. */
    protected final double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     * @param radius the radius value of the geometry
     */
    public RadialGeometry(double radius) {

        this.radius = radius;
    }
    /*
     * Returns the radius value of the geometry.
     */
    public double getRadius() {
        return radius;
    }
}
