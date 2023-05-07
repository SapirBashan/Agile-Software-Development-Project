package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight implements LightSource{
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(max(0, this.direction.normalize().dotProduct(super.getL(p))));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
