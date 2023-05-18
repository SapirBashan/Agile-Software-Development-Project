package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Tube class represents a tube in a three-dimensional space.
 * A tube is defined by a center axis ray and a radius value.
 */
public class Tube extends RadialGeometry {

    /** The axis ray of the tube. */
    protected final Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius.
     * @param radius the radius value of the tube
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns the normal vector to the tube at the specified point.
     * @param p a point on the tube (unused in this implementation)
     * @return the normal vector to the tube
     */
    @Override
    public Vector getNormal(Point p) {
        double t;
        //this try function checks if the point is on the edge of the tube
        try {
            //if the point is on the edge of the tube return the direction of the axis ray
            t = this.axisRay.getDir().normalize().dotProduct(p.subtract(this.axisRay.getP0()));
        }
        catch (IllegalArgumentException e){
            //if the point is not on the edge of the tube
            return (p.subtract(this.axisRay.getP0())).normalize();
        }
        //if the point is on the side of the tube
        Point o = this.axisRay.getP0().add(this.axisRay.getDir().scale(t));
        //if the point is not on the side of the tube
        return p.subtract(o).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Point Pa = axisRay.getP0();
        Vector Va = axisRay.getDir();

        //At^2 + Bt + C equation.
        double A, B, C;

        //(v,u) = v dot product u

        //calculate the vector: V-(V,Va)Va
        Vector VecA = v;
        double Vva = v.dotProduct(Va);

        try {
            if (!isZero(Vva))
                VecA = v.subtract(Va.scale(Vva));

            //A = (V-(V,Va)Va)^2
            A = VecA.lengthSquared();
        }

        //if A = 0 return null (there are no intersections)
        catch (IllegalArgumentException ex) { // the ray is parallel to the axisRay
            return null;
        }

        //if A != 0 continue to calculate B and C
        try {

            //calculate deltaP (delP) vector, P-Pa
            Vector DeltaP = P0.subtract(Pa);

            //The vector: delP - (delP,Va)Va
            Vector DeltaPMinusDeltaPVaVa = DeltaP;
            double DeltaPVa = DeltaP.dotProduct(Va);

            if (!isZero(DeltaPVa))
                DeltaPMinusDeltaPVaVa = DeltaP.subtract(Va.scale(DeltaPVa));

            //B = 2(V - (V,Va)Va , delP - (delP,Va)Va)
            B = 2 * (VecA.dotProduct(DeltaPMinusDeltaPVaVa));

            //C = (delP - (delP,Va)Va)^2 - r^2
            C = DeltaPMinusDeltaPVaVa.lengthSquared() - radius * radius;
        }
        //in case delP = 0, or delP - (delP,Va)Va = (0, 0, 0)
        catch (IllegalArgumentException ex) {
            B = 0;
            C = -1 * radius * radius;
        }

        //solving At^2 + Bt + C = 0

        //the discrimation, B^2 - 4AC
        double Disc = alignZero(B * B - 4 * A * C);

        //no solutions for the equation. disc = 0 means that the ray parallel to the tube
        if (Disc <= 0)
            return null;

        //the solutions for the equation
        double t1, t2;

        t1 = alignZero(-B + Math.sqrt(Disc)) / (2 * A);
        t2 = alignZero(-B - Math.sqrt(Disc)) / (2 * A);

        //taking all positive solutions
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)), new GeoPoint(this, ray.getTargetPoint(t2)));
        if (t1 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
        if (t2 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));

        //all non-positive solutions
        return null;    }
}
