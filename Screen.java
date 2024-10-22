import java.util.*;

public class Screen {
    private String name;
    private ArrayList<GUIComponent> entities = new ArrayList<GUIComponent>();

    public Screen(String name, ArrayList<GUIComponent> e) {
        this.name = name;
        entities = e;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GUIComponent> getButtons() {
        return entities;
    }
}
