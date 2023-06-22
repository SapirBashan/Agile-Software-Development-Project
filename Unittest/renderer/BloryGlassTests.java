package renderer;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class BloryGlassTests {

    private Scene scene = new Scene("Test scene");

    /** Produce a picture of a sphere lighted by a spot light */
    /** Produce a picture of a sphere lighted by a spot light */
    @Test
    public void bloryGlass() {
        Camera camera = new Camera(new Point(0, -10, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);


        scene.geometries.add( //
                new Sphere(20, new Point(0,10, -500)).setEmission(new Color(blue)) //
                        .setMaterial(new Material().setkD(0.4).setkS(1).setShininess(100).setkT(0.3)),
                new Polygon(new Point(-40,40,0), new Point(-40, 0, 0), new Point(40,0,0),
                        new Point(40,40,0)) //
                        .setMaterial(new Material().setkD(0.2).setkR(0.9).setkT(0.6).setkS(0.2))
        );

        scene.lights.add( //
                new PointLight(new Color(WHITE), new Point(0, 10, 50)) //
                        .setKL(0.0004).setKQ(0.0000006));

        ImageWriter imageWriter = new ImageWriter("bloryGlass", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setRayNumRefraction(9).setRayNumReflection(9).setAngle(0.002))
                .renderImage()
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
                .setRayTracer(new RayTracerBasic(scene).setRayNumReflection(1).setAngle(0.004)) //
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
                        .setMaterial(new Material().setkD(0.3).setkR(0.7).setkT(0.6).setkS(0.15).setShininess(4))


        );

        scene.lights.add(new PointLight(new Color(WHITE), new Point(0, 80, 0)) //
                .setKL(4E-5).setKQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("pictureGlossy", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setRayNumRefraction(3).setRayNumReflection(3).setAngle(0.004)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void AntiAliasing() {
        Color KREAM = new Color(255,253,208);
        Material Wall = new Material().setkD(0.05).setkS(0.05).setShininess(2).setkT(1);

        Camera camera = new Camera(new Point(0, 25, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000).setRayNumAntiAliasing(5);

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
                        .setMaterial(new Material().setkD(0.1).setkS(0.25).setShininess(30).setkT(0.7))
        );

        scene.lights.add(new PointLight(new Color(WHITE), new Point(0, 80, 0)) //
                .setKL(4E-5).setKQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("pictureAntiAliasing", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void pictureGlo() {
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
                        .setMaterial(new Material().setkD(0.3).setkR(0.2).setkT(0.5).setkS(0.6).setShininess(4))
        );

        scene.lights.add(new PointLight(new Color(WHITE), new Point(0, 80, 0)) //
                .setKL(4E-5).setKQ(2E-7));


        ImageWriter imageWriter = new ImageWriter("pictureGlo", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).setAngle(0.004).setRayNumReflection(9).setRayNumRefraction(9)) //
                .renderImage() //
                .writeToImage();
    }
}