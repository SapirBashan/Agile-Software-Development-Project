package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class integrationTest {
    Point p0 = new Point(0, 0, 0);
    Vector vTo = new Vector(0, 0, -1);
    Vector vUp = new Vector(0, 1, 0);
    //construct a camera
    Camera camera = new Camera(p0, vTo, vUp).setVPSize(3, 3).setVPDistance(1);

    /**
     * count the number of intersections of a ray with a geometry
     * @param entity the geometry
     * @return the number of intersections
     */
    public int countIntersections(Intersectable entity) {
        int sum = 0;
        Ray ray;
        List<Point> result;
        //for each pixel in the view plane
        //count the number of intersections
        //add the number of intersections to sum
        //i for rows, j for columns
        for(int i=0; i<3;i++){
            for (int j=0; j<3; j++){
                //construct a ray through pixel[i,j]
                ray = camera.constructRay(3, 3, j, i);
                //count the number of intersections
                result = entity.findIntersections(ray);
                //if there are intersections, add the number of intersections to sum
                if(result!=null){
                    sum += result.size();
                }
            }
        }
        return sum;
    }

    @Test
    public void TestIntegrationSphere() {
        //TC01: sphere r = 1
        Point p1 = new Point(0, 0, -3);
        Sphere sphere1 = new Sphere(1, p1);
        assertEquals(2, countIntersections(sphere1), "Wrong number of points");

        //TC02: sphere r = 2.5
        Point p2 = new Point(0, 0, -2.5);
        Sphere sphere2 = new Sphere(2.5, p2);
        p0 = new Point(0, 0, 0.5);
        camera = new Camera(p0, vTo, vUp).setVPSize(3, 3).setVPDistance(1);
        assertEquals(18, countIntersections(sphere2), "Wrong number of points");

        //TC03: sphere r = 2
        Point p3 = new Point(0, 0, -2);
        Sphere sphere3 = new Sphere(2, p3);
        assertEquals(10, countIntersections(sphere3), "Wrong number of points");

        //TC04: sphere r = 4
        Point p4 = new Point(0, 0, -1);
        Sphere sphere4 = new Sphere(4, p4);
        assertEquals(9, countIntersections(sphere4), "Wrong number of points");

        //TC05: 0 intersections
        Point p5 = new Point(0, 0, 1);
        Sphere sphere5 = new Sphere(0.5, p5);
        assertEquals(0, countIntersections(sphere5), "Wrong number of points");
    }

    @Test
    public void TestIntegrationPlane() {
        //TC01: plane parallel to the view plane (9 Points)
        Point p1 = new Point(0, 0, -2);
        Point p2 = new Point(0, 1, -2);
        Point p3 = new Point(1, 0, -2);
        Plane plane1 = new Plane(p1, p2, p3);
        assertEquals(9, countIntersections(plane1), "Wrong number of points");

        //TC02: plane not parallel to the view plane (9 Points)
        Point p4 = new Point(0, 0, -2);
        Point p5 = new Point(3, 3, -1);
        Point p6 = new Point(0,-3 ,-3);
        Plane plane2 = new Plane(p4, p5, p6);
        assertEquals(9, countIntersections(plane2), "Wrong number of points");

        //TC03: 6 intersections (6 Points)
        Point p7 = new Point(-1.5,1.5, -1);
        Point p8 = new Point(-1.5, -1.5, -1);
        Point p9 = new Point(0, 0, -4);
        Plane plane3 = new Plane(p7, p8, p9);
        assertEquals(6, countIntersections(plane3), "Wrong number of points");
    }

    @Test
    public void TestIntegrationTriangle() {
        //TC01: triangle parallel to the view plane (1 Points)
        Point p1 = new Point(0, 1, -2);
        Point p2 = new Point(1, -1, -2);
        Point p3 = new Point(-1, -1, -2);
        Triangle triangle1 = new Triangle(p1, p2, p3);
        assertEquals(1, countIntersections(triangle1), "Wrong number of points");

        //TC02: triangle not parallel to the view plane (2 Points)
        Point p4 = new Point(0, 20, -2);
        Point p5 = new Point(1, -1, -2);
        Point p6 = new Point(-1, -1, -2);
        Triangle triangle2 = new Triangle(p4, p5, p6);
        assertEquals(2, countIntersections(triangle2), "Wrong number of points");
    }
}
