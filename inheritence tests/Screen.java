import java.util.*;

public class Screen {
    ArrayList<Entity> entities = new ArrayList<Entity>();
    boolean active;

    public Screen(ArrayList<Entity> e, boolean a) {
        entities = e;
        active = a;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setActive(boolean a) {
        active = a;
    }
    public boolean getActive() {
        return active;
    }
}
