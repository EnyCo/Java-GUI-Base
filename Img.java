import java.awt.*;
import java.awt.image.BufferedImage;

public class Img extends GUIcomponent {
    private boolean visible;
    private int x, y;
    private int width, height;
    private BufferedImage img = null;
    private String name = "";
    
    public Img (boolean visible, int x, int y, int width, int height, BufferedImage img, String name) {
        super(visible, x, y, width, height);
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.name = name;
        this.img = img;
    }

    public void drawGUIcomponent(Graphics2D g2d) {
        if (visible){
            g2d.drawImage(img, x, y, null);
        }
    }

    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
        width = img.getWidth();
        height = img.getHeight();
    }

    public String getName(){
        return name;
    }
}
