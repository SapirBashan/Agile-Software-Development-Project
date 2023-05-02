package renderer;

import scene.Scene;
import primitives.Color;
import primitives.Point;
import primitives.Ray;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    /**
     * Constructor for RayTracerBase class.
     * @param myScene the scene
     */
    public RayTracerBasic(Scene myScene) {
        super(myScene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = myScene.geometries.findIntersections(ray);
        if(intersections == null){
            return this.myScene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculate the color of the ray
     * @param point the ray
     * @return the color
     */
    private Color calcColor(Point point){
        return this.myScene.ambientLight.getIntensity();
    }
}
