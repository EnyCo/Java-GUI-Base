import java.util.*;

public class Screen {
    String name;
    ArrayList<Button> entities = new ArrayList<Button>();
    boolean active;

    public Screen(String name, ArrayList<Button> e, boolean active) {
        this.name = name;
        entities = e;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Button> getButtons() {
        return entities;
    }

    public void setActive(boolean a) {
        // possibly return this? for 
        if (a) {
            Main.activeScreen = this;
            System.out.println(Main.activeScreen.getName() + " is the active screen now!");
        }
        active = a;
    }
    public boolean getActive() {
        return active;
    }
}
