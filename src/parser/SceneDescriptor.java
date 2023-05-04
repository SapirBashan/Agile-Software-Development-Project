package parser;

import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class SceneDescriptor {

    private Color background;
    private AmbientLight ambientLight;
    private List<Intersectable> geometries;

    public SceneDescriptor(){
        this.background = Color.BLACK;
        this.ambientLight = AmbientLight.NONE;
        this.geometries = new LinkedList<>();
    }
    public void InitializeFromXMLstring(String xmlFile){
        SceneXMLParser sceneXMLParser = new SceneXMLParser(xmlFile);
        this.background = sceneXMLParser.getBackground();
        this.ambientLight = sceneXMLParser.getAmbientLight();
        this.geometries = sceneXMLParser.getGeometries();
    }

    public Scene loadScene(Scene scene){
        scene.setBackground(this.background).setAmbientLight(this.ambientLight);
        for(Intersectable i : geometries){
            scene.geometries.add(i);
        }
        return scene;
    }

}
