package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point position;

    private double kC = 1, kL = 0, kQ = 0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }


    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(this.position);
        return super.getIntensity().scale(1.0/(kC + kL*d + kQ * d*d));
    }

    @Override
    public double getDistance(Point p) {
        return p.distance(this.position);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
