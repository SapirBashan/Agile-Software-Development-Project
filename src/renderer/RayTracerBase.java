package renderer;

import scene.Scene;
import primitives.Color;
import primitives.Ray;

/**
 * RayTracerBase is an abstract class that represents a ray tracer base.
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor for RayTracerBase class.
     * @param myScene the scene
     */
    public RayTracerBase(Scene myScene) {
        this.scene = myScene;
    }
    /**
     * Trace ray.
     * @param ray the ray
     * @return the color
     */
    public abstract Color traceRay(Ray ray);

}
