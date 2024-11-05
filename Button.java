import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Button extends GUIcomponent {
    private boolean visible;
    private int x, y;
    private int width, height;
    private BufferedImage img = null;
    private String name = "";
    
    public Button (boolean visible, int x, int y, int width, int height, String name, BufferedImage img) {
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

    public void drawGUIcomponent(Graphics2D g2d) {
        if (visible){
            if (img != null) {
                g2d.drawImage(img, x, y, null);
            } else {
                g2d.setColor( Color.BLACK );
                Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                    name).getPixelBounds(null, x, y);
                g2d.drawString(name, 
                    x + width/2 - (int)(text.getWidth()/2),
                    y + height/2 + (int)(text.getHeight()/2)); 
                g2d.drawRect(x, y, width, height);
            }
        }
    }

    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
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
