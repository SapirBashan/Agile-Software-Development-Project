package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight implements LightSource{
    private final Vector direction;
    private double narrowBeam;

    //in this constructor we set the narrow beam to 1 by default
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
        this.narrowBeam = 1;
    }

    /**
     * this constructor is for the narrow beam of the light
     *
     * @param intensity the intensity of the light
     * @param position the position of the light
     * @param direction the direction of the light
     * @param narrowBeam the narrow beam of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction, double narrowBeam) {
        super(intensity, position);
        this.direction = direction;
        this.narrowBeam = narrowBeam;
    }
    /**
     * this function returns the direction of the light normalized
     *
     * @param narrowBeam the narrow beam of the light
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    //this function is for the formula of the intensity of the spot light
    //it works with the formula of the point light and the formula of the spot light
    //the explanation of the formula is in the report
    //the formula is: I = I0 * max(0, L * V)^n
    //wich I0 is the intensity of the light
    //L is the direction of the light
    //V is the direction of the vector from the light to the point
    //and the calculation of all the paramiters returns the intensity of the light in the point
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).
                scale(Math.pow(max(0, this.direction.normalize().dotProduct(super.getL(p))), this.narrowBeam));
    }

    //this function is for the formula of the distance of the spot light
    //the function get l returns the direction of the light normalized
    @Override
    public Vector getL(Point p) {
        return super.getL(p).normalize();
    }
}
