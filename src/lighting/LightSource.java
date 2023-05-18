package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {
    /**
     * @param p the point to calculate the intensity at
     * @return the intensity at the point p
     */
    public Color getIntensity(Point p);
    /**
     * @param p the point to calculate the distance from
     * @return the distance from the light source to the point p
     */
    public double getDistance(Point p);
    /**
     * @param p the point to calculate the vector from
     * @return the vector from the light source to the point p
     */
    public Vector getL(Point p);
}
