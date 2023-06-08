package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Blackboard {

    private double length = 700;
    private double angle = 0;
    private Ray basicRay;
    private Vector XDirection;
    private Vector YDirection;
    private List<Point> pixels = new LinkedList<>();
    private int amountOfRays = 1;

    public Blackboard(Ray ray, int amountOfRays){
        this.basicRay = ray;
        this.amountOfRays = amountOfRays;
        this.XDirection = new Vector(ray.getDir().getY()*-1,ray.getDir().getX(),0).normalize();
        this.YDirection = (ray.getDir().crossProduct(XDirection)).normalize();
    }

    public Blackboard setLength(double length){
        this.length = length;
        return this;
    }

    public Blackboard setAngle(double angle){
        if(angle < 0 || angle >= 90)
            throw new IllegalArgumentException("angle must be between 0 to 90 (without 90)");
        this.angle = angle;
        return this;
    }

    public Blackboard setAmountOfRays(int amountOfRays) {
        this.amountOfRays = amountOfRays;
        return this;
    }

    public List<Point> getPixels(){
        Point p = basicRay.getP0().add(basicRay.getDir().scale(length));
        if(this.amountOfRays == 1){
            pixels.add(p);
            return this.pixels;
        }

        double XDirectionLength;
        double YDirectionLength;
        double width = Math.tan(this.angle)*this.length;

/*
        for(int i = 0; i < Math.sqrt(this.amountOfRays - 1) + 1 ; i++){
            for(int j = 0; j < Math.sqrt(this.amountOfRays - 1) + 1 ; j++) {
                XDirectionLength = ((Math.random() - 0.5) * 2) * width;//*width;
                YDirectionLength = (Math.random() - 0.5) * 2 * width;//*width;
                pixels.add(p.add(XDirection.scale(XDirectionLength + (width * i)))
                        .add(YDirection.scale(YDirectionLength + (width * j))));
            }
        }
*/


        for(int i = 0; i < this.amountOfRays; i++) {
            XDirectionLength = ((Math.random() - 0.5)*2) * width;//*width;
            YDirectionLength = ((Math.random() - 0.5)*2) * width;//*width;
            pixels.add(p.add(XDirection.scale(XDirectionLength))
                    .add(YDirection.scale(YDirectionLength)));
        }

        return pixels;
    }


    }
