/*package primitives;

import renderer.Blackboard;

import java.util.LinkedList;
import java.util.List;

public class RayBeam{
    private Ray basicRay;
    private List<Ray> beam;
    private Blackboard targetArea = null;

    /**
     * constructor of RayBeam
     *
     * @param basicRay
     * @param targetArea

    public RayBeam(Ray basicRay,Blackboard targetArea) {
        this.targetArea = targetArea;
        this.basicRay = basicRay;
        createBeam();
    }

    /**
     * returns a list of rays
     *
     * @return beam

    public List<Ray> getBeam() {
        return beam;
    }

    /**
     * create the beam of rays that will be sent to the target area

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
*/