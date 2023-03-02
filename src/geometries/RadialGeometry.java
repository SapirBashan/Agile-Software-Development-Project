package geometries;

abstract class RadialGeometry implements Geometry{
    protected double radios;

    public RadialGeometry(double radios) {
        this.radios = radios;
    }
}
