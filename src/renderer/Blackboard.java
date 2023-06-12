package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Blackboard {

    private double length = 700;
    private double angle = 0.004;
    private Ray basicRay;
    private Vector XDirection;
    private Vector YDirection;
    private List<Point> points = new LinkedList<>();
    private int amountOfRays = 1;

    /**
     * constructor of Blackboard
     * @param ray the basic ray
     * @param amountOfRays the amount of rays that will be sent to the target area
     */
    public Blackboard(Ray ray, int amountOfRays){
        this.basicRay = ray;
        this.amountOfRays = amountOfRays;
        this.XDirection = new Vector(ray.getDir().getY()*-1,ray.getDir().getX(),0).normalize();
        this.YDirection = (ray.getDir().crossProduct(XDirection)).normalize();
    }

    /**
     * set the length of the target area
     * @param length the length of the target area
     */
    public Blackboard setLength(double length){
        this.length = length;
        return this;
    }

    /**
     * set the angle of the target area
     * @param angle the angle of the target area
     */
    public Blackboard setAngle(double angle){
        if(angle < 0 || angle >= 90)
            throw new IllegalArgumentException("angle must be between 0 to 90 (without 90)");
        this.angle = angle;
        return this;
    }

    /**
     * set the amount of rays that will be sent to the target area
     * @param amountOfRays the amount of rays that will be sent to the target area
     */
    public Blackboard setAmountOfRays(int amountOfRays) {
        this.amountOfRays = amountOfRays;
        return this;
    }

    /**
     * returns a list of points
     * the points are the pixels in the target area
     * @return pixels
     */
    public List<Point> getPoints(){
        Point p = basicRay.getP0().add(basicRay.getDir().scale(length));
        if(this.amountOfRays == 1){
            points.add(p);
            return this.points;
        }

        double XDirectionLength;
        double YDirectionLength;
        double width = Math.tan(this.angle)*this.length;


        /**
         * the loop creates random rays in the target area
         * the target area is a rectangle
         * the rectangle is defined by the basic ray and the angle
         */
        for(int i = 0; i < this.amountOfRays; i++) {
            XDirectionLength = ((Math.random() - 0.5)*2) * width;//*width;
            YDirectionLength = ((Math.random() - 0.5)*2) * width;//*width;
            points.add(p.add(XDirection.scale(XDirectionLength))
                    .add(YDirection.scale(YDirectionLength)));
        }

        return points;
    }


    }
