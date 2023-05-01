package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    private Color intensity;
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    public AmbientLight(Color IA, Double3 KA ) {
       intensity = new Color(IA.scale(KA).getColor());
    }

    public AmbientLight(Color IA, double KA){
        intensity = new Color(IA.scale(KA).getColor());
    }

    public Color getIntensity(){
        return intensity;
    }



}
