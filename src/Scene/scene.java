package Scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public void scene(String name){
        this.name = name;
        background = Color.BLACK;
        ambientLight = AmbientLight.NONE;
        geometries = new Geometries();
    }

    public scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
