package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;


class ImageWriterTest {

    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    void testWritePixel() {
        Color Blue = new Color(0.0, 0.0, 200.0);
        Color Red = new Color(200.0, 0.0, 0.0);
        ImageWriter myImg = new ImageWriter("myImg", 800, 500);
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 1, 0));

        //this call to renderImage() is for the grid
        //and it makes the grid blue with a red background
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    myImg.writePixel(i, j, Blue);
                } else {
                    myImg.writePixel(i, j, Red);
                }
            }
        }
        myImg.writeToImage();
    }
}