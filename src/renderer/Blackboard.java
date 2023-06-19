package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Blackboard {

    private Point o;
    private Vector XDirection;
    private Vector YDirection;
    private double size = 1;
    private double length = 700;
    private int amount = 1;

    public Blackboard(Ray ray) {
        this.o = ray.getP0().add(ray.getDir().scale(length));
        try {

            this.XDirection = new Vector(ray.getDir().getY() * -1, ray.getDir().getX(), 0).normalize();
        }
        catch (IllegalArgumentException e) {
            this.XDirection = new Vector(ray.getDir().getZ(), 0, 0).normalize();
        }
        this.YDirection = (ray.getDir().crossProduct(XDirection)).normalize();
    }

    public Blackboard setSize(double size){
        this.size = size;
        return this;
    }

    public double getSize() {
        return size;
    }

    public Vector getXDirection() {
        return XDirection;
    }

    public Vector getYDirection() {
        return YDirection;
    }

    public Blackboard setAmount(int amount){
        this.amount = amount;
        return this;
    }

    public Point getO(){
        return this.o;
    }

    public Blackboard setO(Point o){
        this.o = o;
        return this;
    }

    public double getLength(){
        return this.length;
    }

    public List<Point> getPoints(){
        if(amount == 1){
            return List.of(this.o);
        }

        Point p = o.add(XDirection.scale(-1*size).add(YDirection.scale(size)));
        double XLen, YLen;
        double interval = this.size / amount;

        List<Point> points = new LinkedList<>();

        for(int i = 0; i < amount; i++){
            for(int j = 0; j < amount; j++){
                XLen = (j != 0) ? j*interval : 0.0000001;
                YLen = (i!=0) ? -1*i * interval : 0.0000001;
                points.add(p.add(XDirection.scale(XLen))
                        .add(YDirection.scale(-1*YLen)));
            }
        }

        return points;
    }

    /*
    public List<Point> getPixels() {
        Point p = basicRay.getP0().add(basicRay.getDir().scale(length));
        if (this.amountOfRays == 1) {
            pixels.add(p);
            return this.pixels;
        }

        double XDirectionLength;
        double YDirectionLength;
        double width = Math.tan(this.angle) * this.length;


        for (int i = 0; i < this.amountOfRays; i++) {
            XDirectionLength = ((Math.random() - 0.5) * 2) * width;//*width;
            YDirectionLength = ((Math.random() - 0.5) * 2) * width;//*width;
            if(XDirectionLength == 0)
                XDirectionLength = 0.00001;
            if(YDirectionLength == 0)
                YDirectionLength = 0.00001;
            pixels.add(p.add(XDirection.scale(XDirectionLength))
                    .add(YDirection.scale(YDirectionLength)));
        }

        return pixels;
    }

     */
}
