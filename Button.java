import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Button extends GUIComponent {
    protected int x, y;
    protected BufferedImage img = null;
    
    public Button (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        this.x = x - width/2;
        this.y = y - height/2;
    }

    public void drawGUIComponent(Graphics2D g2d) {
        /*if (this.name.equals("next!")) {
            System.out.println(visible);
        }*/
        if (visible){
            super.drawGUIComponent(g2d);
            if (img != null) {
                g2d.drawImage(img, x, y, null);
            }            
        }
    }

    public BufferedImage getImg(){
        return img;
    }
    public synchronized void setImg(BufferedImage img){
        this.img = img;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        System.out.println("default abstract button click");
    }
}
