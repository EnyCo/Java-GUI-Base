import java.awt.*;
import java.util.*;

public class Screen extends GUIcomponent{
    private boolean active = false;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    private Color background;
    private String name;
    private ArrayList<Img> imgs;
    private ArrayList<Button> buttons;
    private ArrayList<TextBox> textboxes;
    private ArrayList<Screen> subScreens;

    public Screen(boolean active, int x, int y, int width, int height, Color background, String name, ArrayList<Img> imgs, ArrayList<Button> buttons, ArrayList<TextBox> textboxes, ArrayList<Screen> subScreens) {
        super(active, x, y, width, height);
        this.background = background;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.imgs = imgs;
        this.buttons = buttons;
        this.textboxes = textboxes;
        this.subScreens = subScreens;
    }

    public void setActive(boolean active){
        this.active = active;
        if (active) {
            Main.getActiveScreens().add(this);
        } else {
            Main.getActiveScreens().remove(this);
        }
    }

    public Color getBackground(){
        return background;
    }
    public void setBackground(Color background){
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Img> getImgs() {
        return imgs;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public ArrayList<TextBox> getTextboxes() {
        return textboxes;
    }

    public ArrayList<Screen> getSubScreens() {
        return subScreens;
    }
}
