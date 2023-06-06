package renderer;

import geometries.Polygon;
import geometries.Sphere;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class BloryGlassTests {

    private Scene scene = new Scene("Test scene");

    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void bloryGlass() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(20d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setkT(0.3)),
                new Polygon(new Point(-40,40,0), new Point(-40, 0, 0), new Point(40,0,0), new Point(40,40,0)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0).setkT(0.7).setkS(0.5).setShininess(50)));

        scene.lights.add( //
                new PointLight(new Color(WHITE), new Point(-50, 50, 500)) //
                        .setKL(0.0004).setKQ(0.0000006));

        ImageWriter imageWriter = new ImageWriter("bloryGlass", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setAmountOfRays(400)) //
                .renderImage() //
                .writeToImage();
    }

}
