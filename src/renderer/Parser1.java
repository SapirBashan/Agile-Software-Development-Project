package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import scene.Scene;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static java.lang.Double.parseDouble;

public class Parser1 {

    ImageWriter imageWriter;
    static File xmlSceneFile;

    public static void createScene(Scene scene,String fileName) throws ParserConfigurationException, IOException, SAXException {
        xmlSceneFile = new File(fileName);

        if(xmlSceneFile == null){
            throw new IllegalArgumentException("text isn't open");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlSceneFile);

        Node root = doc.getDocumentElement();

        String bg = ((Element)(root)).getAttribute("background-color");
        String[] l = bg.split(" ");
        scene.setBackground(new Color(parseDouble(l[0]), parseDouble(l[1]), parseDouble(l[2])));

        NodeList curTag = doc.getElementsByTagName("ambient-light");
        String al = ((Element)(curTag.item(0))).getAttribute("color");
        l = al.split(" ");
        scene.setAmbientLight(new AmbientLight(new Color(parseDouble(l[0]), parseDouble(l[1]), parseDouble(l[2])), new Double3(1,1,1)));

        curTag = doc.getElementsByTagName("sphere");
        for(int i = 0; i < curTag.getLength(); i++) {
            double radius = parseDouble(((Element)((NodeList)(curTag.item(i)))).getAttribute("radius"));
            String s = ((Element)((NodeList)(curTag.item(i)))).getAttribute("center");
            Point center = strToPoint(s);
            scene.geometries.add(new Sphere(radius, center));
        }

        curTag = doc.getElementsByTagName("triangle");
        for(int i = 0; i < curTag.getLength(); i++) {
            String s = ((Element)((NodeList)(curTag.item(i)))).getAttribute("p0");
            Point p0 = strToPoint(s);
            s = ((Element)((NodeList)(curTag.item(i)))).getAttribute("p1");
            Point p1 = strToPoint(s);
            s = ((Element)((NodeList)(curTag.item(i)))).getAttribute("p2");;
            Point p2 = strToPoint(s);
            scene.geometries.add(new Triangle(p0, p1, p2));
        }
    }

    private static Point strToPoint(String s){
        String[] l = s.split(" ");
        return new Point(parseDouble(l[0]), parseDouble(l[1]), parseDouble(l[2]));
    }
}
