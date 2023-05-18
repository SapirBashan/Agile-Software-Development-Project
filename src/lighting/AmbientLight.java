package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light {
    //this field is for the constructor with two parameter color and double3
    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
    //this field is for the constructor with two parameter color and double3
    public AmbientLight(Color IA, Double3 KA ) {
        super(IA.scale(KA));
    }
    //this field is for the constructor with two parameters color and double
    public AmbientLight(Color IA, double KA){
        super(IA.scale(KA));
    }

}
