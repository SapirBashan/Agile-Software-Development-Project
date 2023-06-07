package renderer;

import geometries.Polygon;
import geometries.Sphere;
import lighting.PointLight;
import lighting.SpotLight;
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
        Camera camera = new Camera(new Point(0, -10, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(10, new Point(0,20, -200)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setkT(0.3)),
                new Polygon(new Point(-40,40,0), new Point(-40, 0, 0), new Point(40,0,0),
                        new Point(40,40,0)) //
                        .setMaterial(new Material().setkD(0.3).setkR(0.2).setkT(0.6).setkS(0.15).setShininess(4))
        );

        scene.lights.add( //
                new PointLight(new Color(WHITE), new Point(0, 150, 300)) //
                        .setKL(0.0004).setKQ(0.0000006));

        ImageWriter imageWriter = new ImageWriter("bloryGlass", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setRayNumRefraction(10).setRayNumReflection(10)) //
                .renderImage() //
                .writeToImage();
    }


    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void Glass() {
        Camera camera = new Camera(new Point(0, 50, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(10d, new Point(0,20, 70)).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.3).setShininess(100).setkT(0.3)),
                new Polygon(new Point(-40,10,-100), new Point(-40, 0, 300), new Point(40,0,300),
                        new Point(40,10,-100)).setEmission(new Color(0,100,220)) //
                        .setMaterial(new Material().setkD(0.6).setkR(1).setkT(0.8).setkS(0.8).setShininess(100))
        );

        scene.lights.add( //
                new PointLight(new Color(WHITE), new Point(0, 100, 300)) //
                        .setKL(0.0004).setKQ(0.0000006));
        //scene.lights.add( new DirectionalLight(new Color(92,92,92), new Vector(1,1,0)));

        ImageWriter imageWriter = new ImageWriter("Glass", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setRayNumAntiAliasing(10)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Glass2() {

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add(
                new Sphere(50, new Point(0, 0, -200))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setkT(0.3)),

                new Polygon(new Point(-100, -100, 0), new Point(-100, 100, 0), new Point(100, 100, 0), new Point(100, -100, 0))
                        .setMaterial(new Material().setkD(0.3).setkR(0.2).setkT(0.8).setkS(0.15).setShininess(4))
        );

        scene.lights.add(
                new SpotLight(new Color(WHITE), new Point(-200, 200, 200), new Vector(1, -1, -1))
                        .setKL(0.0001).setKQ(0.000005)
        );

        ImageWriter imageWriter = new ImageWriter("gloryGlass2", 500, 500);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene).setRayNumRefraction(1).setRayNumReflection(1))
                .renderImage()
                .writeToImage();
    }
}
