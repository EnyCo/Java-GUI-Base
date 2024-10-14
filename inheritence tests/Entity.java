import java.awt.*;

public class Entity {
    private Point loc;
    private int width;
    private int height;

    public Entity (int x, int y, int w, int h) {
        loc = new Point(x, y);
        width = w;
        height = h;
    }
    public Entity (Point l, int w, int h) {
        loc = l;
        width = w;
        height = h;
    }

    public void setLoc(Point l) {
        loc = l;
    }
    public void setLoc(int x, int y) {
        loc = new Point(x, y);
    }

    public Point getLoc() {
        return loc;
    }
    public int getX() {
        return (int)loc.getX();
    }
    public int getY() {
        return (int)loc.getY();
    }


    public void setWidth(int w) {
        width = w;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int h) {
        height = h;
    }
    public int getHeight() {
        return height;
    }

    public boolean isHover(Point m){
        return (int)m.getX() >= getX() && (int)m.getX() <= getX() + width 
            && (int)m.getY() >= getY() && (int)m.getY() <= getY() + height;
    }
    public boolean isHover(int x, int y){
        return x > getX() && x <= getX() + width 
            && y > getY() && y <= getY() + height;
    }
    
}

