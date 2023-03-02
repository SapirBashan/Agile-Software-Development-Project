package geometries;
import primitives.Point;
import primitives.*;

public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    public Point getQ0() {
        return q0;
    }


    public Plane(Point p1, Point p2, Point p3) {
       q0 = p1;
       normal = null;
    }
    public Plane(Point p0, Vector vNormal){
        q0 = p0;
        normal = vNormal.normalize();
    }
    public Vector getNormal() {
        return normal;
    }
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
