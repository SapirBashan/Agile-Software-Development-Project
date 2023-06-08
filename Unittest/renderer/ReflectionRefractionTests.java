/**
 *
 */
package renderer;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setkT(0.3)),
                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKL(0.0004).setKQ(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }


    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(400d,new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere(200d,new Point(-950, -900, -1000) ).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKL(0.00001).setKQ(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /** Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60)), //
                new Sphere(30d,new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setkT(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKL(4E-5).setKQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setRayNumAntiAliasing(1)) //
                .renderImage() //
                .writeToImage();
    }


    @Test
    public void pictureForBonus() {
        Color KREAM = new Color(255,253,208);
        Material Wall = new Material().setkD(0.05).setkS(0.05).setShininess(2).setkT(1);

        Camera camera = new Camera(new Point(0, 25, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Polygon(new Point(-100,-20, 500), new Point(-100,-20, -500),new Point(100,-20, -500),new Point(100,-20, 500))//
                        .setMaterial(Wall)
                        .setEmission(new Color(GRAY)),//
                new Polygon(new Point(-100,100, 500), new Point(-100,100, -500),new Point(100,100, -500),new Point(100,100, 500))//
                        .setMaterial(Wall)
                        .setEmission(new Color(PINK)),//
                new Polygon(new Point(-100,100, -499), new Point(100,100, -499),new Point(100,-20, -499),new Point(-100,-20, -499))//
                        .setMaterial(Wall)
                        .setEmission(new Color(pink)),//
                new Polygon(new Point(-100,100, -499), new Point(-100,100, 500),new Point(-100,-20, 500),new Point(-100,-20, -499))//
                        .setMaterial(Wall)
                        .setEmission(new Color(PINK)),//
                new Polygon(new Point(100,100, -499), new Point(100,100, 500),new Point(100,-20, 500),new Point(100,-20, -499))//
                        .setMaterial(Wall)
                        .setEmission(KREAM)
                        .setEmission(new Color(PINK)),//

                //Mirror
                new Polygon(new Point(-20,50, -498), new Point(20,50, -498),new Point(20,5, -498),new Point(-20,5, -498))//
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(5).setkR(1)),//
                new Polygon(new Point(-20,50, -498), new Point(20,50, -498),new Point(20,5, -498),new Point(-20,5, -498))//
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60).setkR(0.9))
                        .setEmission(new Color(pink)), //

                // Light
                new Sphere(3d,new Point(0, 90, 0)).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setkT(0.3)),//
                new Cylinder(0.2,new Ray(new Point(0, 90, 0), new Vector(0,1,0)),58)//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(GRAY)),//

                // Table

                new Polygon(new Point(-40,6, -499), new Point(40,6, -499),new Point(40,4, -400),new Point(-40,4, -400))//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(BLUE)),//
                new Cylinder(2,new Ray(new Point(-38,4, -497), new Vector(0,-1,0)),25)//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(BLUE)),//
                new Cylinder(2,new Ray(new Point(38,4, -497), new Vector(0,-1,0)),25)//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(BLUE)),//
                new Cylinder(2,new Ray(new Point(38,4, -402), new Vector(0,-1,0)),25)//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(BLUE)),//
                new Cylinder(2,new Ray(new Point(-38,4, -402), new Vector(0,-1,0)),25)//
                        .setMaterial(new Material().setkR(0).setkD(0.5).setkS(0.5).setShininess(5))
                        .setEmission(new Color(BLUE)),//
                // On the Table
                new Sphere(4d,new Point(9, 9, -460)).setEmission(new Color(RED))
                        .setMaterial(new Material().setkD(0.1).setkS(0.25).setShininess(30).setkT(0.7)),//
                new Sphere(1d,new Point(9, 9, -460)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.1).setkS(0.25).setShininess(30).setkT(0.2)),//
                new Sphere(20d,new Point(-70, -20, -100)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setkD(0.1).setkS(0.25).setShininess(30).setkT(0.7)),
                new Polygon(new Point(-30,40,0), new Point(-30, 0, 0), new Point(30,0,0),
                        new Point(30,40,0))
                        .setMaterial(new Material().setkD(0.3).setkR(0.2).setkT(0.6).setkS(0.15).setShininess(4))


        );

        scene.lights.add(new PointLight(new Color(WHITE), new Point(0, 80, 0)) //
                .setKL(4E-5).setKQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("pictureForBonus", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
   }

    @Test
    public void fourObjects() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));


        scene.geometries.add( //
                new Triangle(new Point(-100, -30, 0), new Point(200, 30,30),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60).setkT(0)), //
                new Triangle(new Point(-200, 0, -100), new Point(200, 0,-100),
                        new Point(0, 100, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60).setkR(1)), //
                new Sphere(30d,new Point(0, 0, 0)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.1).setkS(0.25).setShininess(30).setkT(0.6)),
                new Sphere(15d,new Point(0, 0, 0)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.27).setkS(0.2).setShininess(30).setkT(0.6)),
                new Sphere(20d,new Point(50, 30, 0)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setkT(0.6))

        );

        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point(60, 50, 100)) //
                .setKL(4E-5).setKQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("reflectionFourObjects", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
 }

}
