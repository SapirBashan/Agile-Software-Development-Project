package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    /**
     * list of geometries
     */
    private final List<Intersectable> geometries;
    /**
     * default constructor
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }
    /**
     * @param geometries list of geometries
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        add(geometries);
    }
    /**
     * @param geometries list of geometries
     */
    public void add(Intersectable... geometries){
        for(Intersectable i : geometries){
            this.geometries.add(i);
        }
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> temp;
        List<GeoPoint> result = null;
        for(Intersectable i : geometries){
            temp = i.findGeoIntersectionsHelper(ray);
            if(temp != null) {
                if (result == null)
                    result = new LinkedList<>();
                for (GeoPoint p : temp){
                    result.add(p);
                }
            }
        }

        return result;
    }
}
