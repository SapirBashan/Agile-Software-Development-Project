package primitives;

import renderer.Blackboard;

import java.util.ArrayList;
import java.util.List;

public class RayBeam{

    private List<Ray> beam;
    private Blackboard targetArea;
    public RayBeam(Ray ray, Point intersection, int amount) {
        this.beam = getBeam(ray,intersection,amount);
    }

    public List<Ray> getBeam() {
        return beam;
    }

    public List<Ray> getBeam(Ray ray, Point intersection,int amount) {
        if(amount == 1){
            this.beam = List.of(ray);
            return beam;
        }

        targetArea = new Blackboard(ray,intersection);
        beam = new ArrayList<>();
        for (Point p: targetArea.addPixel(amount)) {
            beam.add(new Ray(ray.getP0(),p.subtract(ray.getP0())));
        }

        return beam;
    }
}
