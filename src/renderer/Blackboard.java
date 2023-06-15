package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Blackboard {

    private Point o;
    private Vector XDirection;
    private Vector YDirection;

    private double size;
    private double angle = 0.004;
    private double length = 700;

    public Blackboard(Ray ray) {
        this.o = ray.getP0();
        this.XDirection = new Vector(ray.getDir().getY() * -1, ray.getDir().getX(), 0).normalize();
        this.YDirection = (ray.getDir().crossProduct(XDirection)).normalize();
        calcSize();
    }

    public Blackboard setLength(double length) {
        this.length = length;
        calcSize();
        return this;
    }

    public Blackboard setAngle(double angle) {
        if (angle < 0 || angle >= 90)
            throw new IllegalArgumentException("angle must be between 0 to 90 (without 90)");
        this.angle = angle;
        calcSize();
        return this;
    }

    public double getSize() {
        return size;
    }

    public Point getO() {
        return o;
    }

    public Vector getXDirection() {
        return XDirection;
    }

    public Vector getYDirection() {
        return YDirection;
    }

    public void calcSize(){
        this.size = Math.tan(this.angle) * this.length * 2;
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
