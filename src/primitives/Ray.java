package primitives;

import java.util.Objects;
/**
 * The Ray class represents a ray in 3D space, consisting of an origin point and a direction vector.
 */
public class Ray {
    /**
     * The origin point of the ray.
     */
    private Point p0;
    /**
     * The direction vector of the ray, normalized to unit length.
     */
    private  Vector dir;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    /**
     * Constructs a new Ray object with the given origin point and direction vector.
     *
     * @param p0 the origin point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**
     * Checks if this Ray object is equal to another object.
     *
     * @param o the object to compare this Ray object to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }
    /**
     * Returns a string representation of this ray.
     * @return a string representation of this ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

}
