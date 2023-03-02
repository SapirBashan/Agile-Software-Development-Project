package primitives;


public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is ZERO");
        }
    }

    Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is ZERO");
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    public Vector add(Vector v){
        return new Vector(super.add(v).xyz);
    }

    public Vector scale(Double d){
        return new Vector(this.xyz.scale(d));
    }

    public Vector crossProduct(Vector v){
        double x = (this.xyz.d2 * v.xyz.d3) - (this.xyz.d3 * v.xyz.d2);
        double y = (this.xyz.d3 * v.xyz.d1) - (this.xyz.d1 * v.xyz.d3);
        double z = (this.xyz.d1 * v.xyz.d2) - (this.xyz.d2 * v.xyz.d1);

        return new Vector(x, y, z);
    }

    public double lengthSquared(){
        return this.dotProduct(this);
    }

    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    public  Vector normalize(){
        double x = this.xyz.d1 / this.length();
        double y = this.xyz.d2 / this.length();
        double z = this.xyz.d3 / this.length();

        return new Vector(x, y, z);
    }

    public double dotProduct(Vector v){
        double x = this.xyz.d1 * v.xyz.d1;
        double y = this.xyz.d2 * v.xyz.d2;
        double z = this.xyz.d3 * v.xyz.d3;

        return  x + y + z;
    }
}
