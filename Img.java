import java.awt.*;
import java.awt.image.BufferedImage;

public class Img extends GUIComponent {
    protected boolean visible;
    protected int x, y;
    protected int width, height;
    protected BufferedImage img = null;
    protected String name = "";
    
    public Img (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        this.visible = visible;
        this.x = x - width/2;
        this.y = y - height/2;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public BufferedImage getImg(){
        return img;
    }
    public synchronized void setImg(BufferedImage img){
        this.img = img;
    }

    public void drawGUIComponent(Graphics2D g2d) {
        if (visible){
            if (img != null) {
                g2d.drawImage(img, x, y, null);
            } else {
                super.drawGUIComponent(g2d);
            }
        } 
    }

}
