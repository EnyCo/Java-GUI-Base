import java.awt.image.*;

public abstract class GUIComponent {
    private boolean active = false;
    private int x, y;
    private int width, height;
    private BufferedImage img = null;
    private String text = "";

    public GUIComponent (boolean active, int x, int y, int width, int height, BufferedImage img, String text) {
        this.active = active;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.text = text;
    }

    public boolean getActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }

    public String getText(){
        return text;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        System.out.println("default abstract execute ran");
    }
}
