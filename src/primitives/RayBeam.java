package primitives;

import renderer.Blackboard;

import java.util.LinkedList;
import java.util.List;

public class RayBeam{
    private Ray basicRay;
    private List<Ray> beam;
    private Blackboard targetArea = null;

    public RayBeam(Ray basicRay,Blackboard targetArea) {
        this.targetArea = targetArea;
        this.basicRay = basicRay;
        createBeam();
    }

    public List<Ray> getBeam() {
        return beam;
    }


    private void createBeam(){
        if(this.targetArea == null)
            throw new IllegalArgumentException("the target area is not exist");

        beam = new LinkedList<>();

        List<Point> tarArea = this.targetArea.getPixels();
        for (Point p: tarArea) {
            beam.add(new Ray(basicRay.getP0(),p.subtract(basicRay.getP0())));
        }
    }
}