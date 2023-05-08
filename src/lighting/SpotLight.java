package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight implements LightSource{
    private final Vector direction;
    private double narrowBeam;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
        this.narrowBeam = 1;
    }

    public SpotLight(Color intensity, Point position, Vector direction, double narrowBeam) {
        super(intensity, position);
        this.direction = direction;
        this.narrowBeam = narrowBeam;
    }

    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.pow(max(0, this.direction.normalize().dotProduct(super.getL(p))), this.narrowBeam));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p).normalize();
    }
}
