public class ButtonTest extends Button {
    public ButtonTest (int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public ButtonTest (int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void onClick() {
        if (Main.SCREENS.get(0).getName().equals(Main.activeScreen.getName())) {
            Main.SCREENS.get(0).setActive(false);
            Main.SCREENS.get(1).setActive(true);
        } else {
            Main.SCREENS.get(0).setActive(true);
            Main.SCREENS.get(1).setActive(false);
        }
        
        
    }
}
