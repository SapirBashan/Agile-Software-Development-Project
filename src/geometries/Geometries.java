package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
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

        for(Intersectable i : List.of(geometries)){
            this.geometries.add(i);
        }
    }
    /**
     * @param geometries list of geometries
     */
    public void add(Intersectable... geometries){
        for(Intersectable i : List.of(geometries)){
            this.geometries.add(i);
        }
    }
    /**
     * @param ray the ray that intersect with the geometry
     * @return list of points that the ray intersect with the geometry
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> temp;
        List<Point> result = null;
        for(Intersectable i : geometries){
            temp = i.findIntersections(ray);
            if(temp != null) {
                if (result == null)
                    result = new ArrayList<>();
                for (Point p : temp){
                    result.add(p);
                }
            }
        }

        return result;
    }
}
