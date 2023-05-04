package parser;

import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class SceneXMLParser {
    private SaxHandler SaxHndler;

    public SaxHandler getSaxHndler() {
        return SaxHndler;
    }

    /**
     * Constructor
     * @param fileName
     */
    public SceneXMLParser(String fileName){
        try {
            //Create file object.
            File inputFile = new File(fileName);

            //Get SAXParserFactory instance.
            SAXParserFactory factory=SAXParserFactory.newInstance();

            //Get SAXParser object from SAXParserFactory instance.
            SAXParser saxParser = factory.newSAXParser();

            //Create SaxHndler object.
            SaxHndler = new SaxHandler();

            //Parse the XML file.
            saxParser.parse(inputFile, SaxHndler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Intersectable> getGeometries() {
        return this.SaxHndler.getGeometries();
    }
    public Color getBackground() {
        return this.SaxHndler.getBackground();
    }

    public AmbientLight getAmbientLight() {
        return this.SaxHndler.getAmbientLight();
    }
}
