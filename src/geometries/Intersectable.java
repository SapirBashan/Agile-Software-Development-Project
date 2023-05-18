package geometries;


import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable interface represents an intersectable geometry in a three-dimensional space.
 * An intersectable geometry is defined by a findIntersections method.
 */
public abstract class Intersectable {


    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point){
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof GeoPoint){
                return geometry.equals(((GeoPoint) o).geometry) && point.equals(((GeoPoint) o).geometry);
            }
            return false;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public final List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersectionsHelper(ray);
        if(geoList == null){
            return null;
        }

        return geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
