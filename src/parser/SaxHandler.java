package parser;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class SaxHandler extends DefaultHandler{

    private List<Intersectable> geometries;
    private Color background;
    private AmbientLight ambientLight;

    public List<Intersectable> getGeometries() {
        return geometries;
    }
    public Color getBackground() {
        return this.background;
    }

    public AmbientLight getAmbientLight() {
        return this.ambientLight;
    }

    @Override
    public void startDocument() {
        this.geometries = new LinkedList<>();
        this.background = Color.BLACK;
        this.ambientLight = AmbientLight.NONE;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        Color color;
        if (qName.equalsIgnoreCase("scene")) {
            // get tag's attribute by name

            String colorStr = attributes.getValue("background-color");
            color = strToColor(colorStr);

            this.background = color;
        }

        if (qName.equalsIgnoreCase("ambient-light")) {
            // get tag's attribute by name
            String colorStr = attributes.getValue("color");
            color = strToColor(colorStr);
            double k = parseDouble(attributes.getValue("k"));

            this.ambientLight = new AmbientLight(color, new Double3(k));
        }

        if (qName.equalsIgnoreCase("sphere")) {
            // get tag's attribute by name
            double radius = parseDouble(attributes.getValue("radius"));
            String centerStr = attributes.getValue("center");
            Point center = strToPoint(centerStr);

            this.geometries.add(new Sphere(radius, center));
        }

        if (qName.equalsIgnoreCase("triangle")) {
            // get tag's attribute by index, 0 = first attribute
            Point p0 = strToPoint(attributes.getValue(0));
            Point p1 = strToPoint(attributes.getValue(1));
            Point p2 = strToPoint(attributes.getValue(2));

            this.geometries.add(new Triangle(p0,p1,p2));
        }

        if (qName.equalsIgnoreCase("plane")) {
            // get tag's attribute by index, 0 = first attribute
            Point p0 = strToPoint(attributes.getValue(0));
            Point p1 = strToPoint(attributes.getValue(1));
            Point p2 = strToPoint(attributes.getValue(2));

            this.geometries.add(new Plane(p0,p1,p2));
        }



    }

    private Point strToPoint(String s){
        String[] l = s.split(" ");
        return new Point(parseDouble(l[0]), parseDouble(l[1]), parseDouble(l[2]));
    }

    private Color strToColor(String s){
        String[] l = s.split(" ");
        return new Color(parseDouble(l[0]), parseDouble(l[1]), parseDouble(l[2]));
    }

}
