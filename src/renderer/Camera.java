package renderer;

import primitives.*;
import java.util.MissingResourceException;

/**
 * Camera class represents a camera in the scene
 * The camera is defined by a point p0 and 3 vectors vTo, vUp and vRight
 */
public class Camera {
    final private Point p0;
    final private Vector vTo;
    final private Vector vUp;
    final private Vector vRight;
    private double width;
    private double height;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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
     * @param width the width of the view plane
     * @param height the height of the view plane
     * @return the camera
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }
    /**
     * set the distance between the camera and the view plane
     * @param distance the distance between the camera and the view plane
     * @return the camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * @param imageWriter the width to set
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * @param rayTracer the ray tracer
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return  this;
    }
    /**
     * render the image by tracing rays through each pixel
     * if any of the camera veriables are not initialized throw exception
     * @throws IllegalArgumentException if imageWriter or rayTracer are not initialized
     * @throws IllegalArgumentException if the width or height are not initialized
     */
    public Camera renderImage() throws UnsupportedOperationException{

        try {
            if (imageWriter == null) {
                throw new MissingResourceException("imageWriter is not initialized", "Camera", "imageWriter");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("rayTracer is not initialized", "Camera", "rayTracer");
            }

            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            //for each pixel
            //construct a ray through the pixel
            //trace the ray
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    //trace the ray
                    imageWriter.writePixel(j, i, castRay(nX,nY,j,i));
                }
            }
        }catch (MissingResourceException e){
            throw new UnsupportedOperationException();
        }

        return this;
    }

    /**
     * construct a ray through a pixel
     * @param nX the number of pixels in the x axis
     * @param nY the number of pixels in the y axis
     * @param j the index of the pixel in the x axis
     * @param i the index of the pixel in the y axis
     * @return the ray through the pixel
     */
    private Color castRay(int nX, int nY, int j, int i){
        //construct a ray through the pixel
        Ray ray = constructRay(nX, nY, j, i);
        //trace the ray
        return rayTracer.traceRay(ray);
    }

    /**
     * print a grid on the view plane
     * @param interval the interval between the lines of the grid
     * @param color the color of the grid
     * @throws IllegalArgumentException if imageWriter is not initialized
     */
    public void printGrid(int interval, Color color) throws MissingResourceException{
        if(imageWriter == null)
            throw new MissingResourceException("imageWriter is not initialized", "Camera", "imageWriter");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        //for each pixel
        //if the pixel index is a multiple of the interval
        //print the pixel
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++){
                if(i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * write the image to the file
     * @throws IllegalArgumentException if imageWriter is not initialized
     */
    public void writeToImage() throws MissingResourceException{
        if(imageWriter == null) {
            throw new MissingResourceException("imageWriter is not initialized", "Camera", "imageWriter");
        }
        imageWriter.writeToImage();
    }

    /**
     * construct a ray through a pixel
     * @param nX number of pixels in x axis
     * @param nY number of pixels in y axis
     * @param j pixel index in x axis
     * @param i pixel index in y axis
     * @return the ray through the pixel
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
