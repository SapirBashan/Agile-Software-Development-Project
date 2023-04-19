package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public List<Point> findIntersections(Ray ray);
}
