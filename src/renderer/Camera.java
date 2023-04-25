package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;


/**
 * Camera class represents a camera in the scene
 * The camera is defined by a point p0 and 3 vectors vTo, vUp and vRight
 */
public class Camera {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    //ctor
    public Camera(Point p0,Vector vTo, Vector vUp) {
        try {
            vTo.dotProduct(vUp);
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        }
        catch (IllegalArgumentException e) {
            this.vTo = vTo.normalize();
            this.vUp = vUp.normalize();
            vRight = vTo.crossProduct(vUp).normalize();
            this.p0 = p0;
        }
    }

    public Point getP0() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }
    /**
     * set the view plane size
     * @param width
     * @param height
     * @return
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }
    /**
     * set the distance between the camera and the view plane
     * @param distance
     * @return
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }
    /**
     * construct a ray through a pixel
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        //image center
        Point pC = p0.add(vTo.scale(distance));
        //ratio (pixel width and height)
        double Ry = height / nY;
        double Rx = width / nX;
        //pixel[i,j] center
        double yI = -(i - (nY - 1) / 2d) * Ry;
        double xJ = (j - (nX - 1) / 2d) * Rx;
        Point pIJ = pC;
        //if the view plane is not a square
        if (!Util.isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!Util.isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));
        //ray direction
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }
}
