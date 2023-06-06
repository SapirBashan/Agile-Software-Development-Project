package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Blackboard {

    private Vector XDirection;
    private Vector YDirection;
    private Point point;
    private List<Point> pixels = new ArrayList<>();
    private int amountOfRays = 1;
    public Blackboard setAmountOfRays(int amountOfRays) {
        this.amountOfRays = amountOfRays;
        return this;
    }
    public Blackboard(Ray ray, Point point){
            this.point = point;
            this.XDirection = new Vector(ray.getDir().getY()*-1,ray.getDir().getY(),0).normalize();
            this.YDirection = ray.getDir().crossProduct(XDirection).normalize();
    }

    public List<Point> addPixel(int amountOfRays){
        this.amountOfRays = amountOfRays;

        double XDirectionLength;
        double YDirectionLength;

        for(int i = this.amountOfRays; i > 0; i--){
            XDirectionLength = (Math.random() -0.5) *10;
            YDirectionLength = (Math.random() -0.5) *10;
            pixels.add(point.add(XDirection.scale(XDirectionLength)).add(YDirection.scale(YDirectionLength)));
        }

        return pixels;
    }


    }
