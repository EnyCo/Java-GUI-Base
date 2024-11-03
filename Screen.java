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
        this.active = active;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.name = name;
        this.imgs = imgs;
        this.buttons = buttons;
        this.textboxes = textboxes;
        this.subScreens = subScreens;
    }

    public void drawGUIcomponent(Graphics2D g2d) { // does not print out itself but prints subscreens fine
        if (active) {
            g2d.setColor(background);
            g2d.fillRect(x, y, width,height);
            
            for (int i = 0; i < imgs.size(); i++) {
                imgs.get(i).drawGUIcomponent(g2d);
            }
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).drawGUIcomponent(g2d);
            }
            for (int i = 0; i < textboxes.size(); i++) {
                textboxes.get(i).drawGUIcomponent(g2d);
            }
            
            for (int i = 0; i < subScreens.size(); i++) {
                subScreens.get(i).drawGUIcomponent(g2d);
            }  
        }
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
