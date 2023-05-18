package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public double getDistance(Point p) {
        //this calculation is for the formula of the distance
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public Vector getL(Point p) {
        return this.direction.normalize();
    }
}
