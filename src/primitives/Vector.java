package primitives;

/**
 * The Vector class extends the Point class and represents a mathematical vector in three-dimensional space.
 */
public class Vector extends Point {

    /**
     * Constructs a Vector object with the specified x, y, and z components.
     * @param x the x-component of the vector.
     * @param y the y-component of the vector.
     * @param z the z-component of the vector.
     * @throws IllegalArgumentException if the vector has zero magnitude.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is ZERO");
        }
    }

    /**
     * Constructs a Vector object with the specified Double3 components.
     * @param xyz the components of the vector.
     * @throws IllegalArgumentException if the vector has zero magnitude.
     */
    Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("the vector is ZERO");
        }
    }

    /**
     * Determines whether the specified object is equal to this Vector object.
     * @param o the object to compare with this vector.
     * @return true if the specified object is equal to this Vector object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Returns a string representation of this Vector object.
     *
     * @return a string representation of this Vector object.
     */
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * Returns a new Vector object that is the result of adding this vector with the specified vector.
     *
     * @param v the vector to add.
     * @return the result of adding this vector with the specified vector.
     */
    public Vector add(Vector v){
        return new Vector(super.add(v).xyz);
    }

    /**
     * Scales the vector by the given scalar value.
     *
     * @param d the scalar value to scale the vector by
     * @return a new vector that is the result of scaling this vector by the given scalar value
     */
    public Vector scale(Double d){
        return new Vector(this.xyz.scale(d));
    }

    /**
     * Computes the cross product of this vector with the given vector.
     *
     * @param v the vector to compute the cross product with
     * @return a new vector that is the result of computing the cross product of this vector with the given vector
     */
    public Vector crossProduct(Vector v){
        double x = (this.xyz.d2 * v.xyz.d3) - (this.xyz.d3 * v.xyz.d2);
        double y = (this.xyz.d3 * v.xyz.d1) - (this.xyz.d1 * v.xyz.d3);
        double z = (this.xyz.d1 * v.xyz.d2) - (this.xyz.d2 * v.xyz.d1);

        return new Vector(x, y, z);
    }

    /**
     * Computes the square of the length of the vector.
     *
     * @return the square of the length of the vector
     */
    public double lengthSquared(){
        return this.dotProduct(this);
    }
    /**
     * Computes the length of the vector.
     *
     * @return the length of the vector
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    /**
     * Returns a new vector that is the unit vector of this vector.
     *
     * @return a new vector that is the unit vector of this vector
     */
    public  Vector normalize(){
        double x = this.xyz.d1 / this.length();
        double y = this.xyz.d2 / this.length();
        double z = this.xyz.d3 / this.length();

        return new Vector(x, y, z);
    }
    /**
     * Computes the dot product of this vector with the given vector.
     *
     * @param v the vector to compute the dot product with
     * @return the result of computing the dot product of this vector with the given vector
     */
    public double dotProduct(Vector v){
        double x = this.xyz.d1 * v.xyz.d1;
        double y = this.xyz.d2 * v.xyz.d2;
        double z = this.xyz.d3 * v.xyz.d3;

        return  x + y + z;
    }
}
