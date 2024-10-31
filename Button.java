import java.awt.image.BufferedImage;

abstract public class Button extends GUIcomponent {
    private boolean active;
    private int x, y;
    private int width, height;
    private BufferedImage img = null;
    private String name = "";
    
    public Button (boolean active, int x, int y, int width, int height, BufferedImage img, String name) {
        super(active, x, y, width, height);
        this.active = active;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.name = name;
        this.img = img;
    }

    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }

    public String getName(){
        return name;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        System.out.println("default abstract button click");
    }
}
