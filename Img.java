import java.awt.*;
import java.awt.image.BufferedImage;

public class Img extends GUIComponentVis {
    protected int x, y;
    protected BufferedImage img = null;
    
    public Img (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        this.x = x - width/2;
        this.y = y - height/2;
    }
}
