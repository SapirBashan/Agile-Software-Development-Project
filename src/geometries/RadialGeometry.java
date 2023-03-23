package geometries;

abstract class RadialGeometry implements Geometry{
    final protected double radios;

    public RadialGeometry(double radios) {
        this.radios = radios;
    }
}
