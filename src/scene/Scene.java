package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;


    /**
     * @param name the name to set
     */
    public Scene(String name){
        this.name = name;
        background = Color.BLACK;
        ambientLight = AmbientLight.NONE;
        geometries = new Geometries();
    }

    /**
     * @param background the background to set
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * @return Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * @param geometries the geometries to set
     * @return Scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
