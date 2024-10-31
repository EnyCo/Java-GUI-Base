import java.util.*;

public class Screen {
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    private String name;
    private ArrayList<Button> buttons;
    private ArrayList<TextBox> textboxes;

    public Screen(int x, int y, int width, int height, String name, ArrayList<Button> buttons, ArrayList<TextBox> textboxes) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.buttons = buttons;
        this.textboxes = textboxes;
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

    public String getName() {
        return name;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }
    public ArrayList<TextBox> getTextboxes() {
        return textboxes;
    }
}
