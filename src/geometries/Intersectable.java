package geometries;


import primitives.Point;
import primitives.Ray;

import java.util.List;

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

    public final List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersectionsHelper(ray);
        if(geoList == null){
            return null;
        }

        return geoList.stream().map(gp -> gp.point).toList();
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
