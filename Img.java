import java.awt.*;
import java.awt.image.BufferedImage;

public class Img extends GUIComponent {
    private boolean visible;
    private int x, y;
    private int width, height;
    private BufferedImage img = null;
    private String name = "";
    
    public Img (boolean visible, int x, int y, int width, int height, String name, BufferedImage img) {
        super(visible, x, y, width, height, name);
        this.visible = visible;
        this.x = x - width/2;
        this.y = y - height/2;
        this.width = width;
        this.height = height;
        this.img = img;
        this.name = name;
        this.img = img;
    }

    public void drawGUIComponent(Graphics2D g2d) {
        if (visible){
            g2d.drawImage(img, x, y, null);
        }
    }

    public void setImg(BufferedImage img){
        this.img = img;
        width = img.getWidth();
        height = img.getHeight();
    }
}
