public class ButtonTestStart extends GUIComponent {
    public ButtonTestStart (boolean active, int x, int y, int width, int height, String text) {
        super(active, x, y, width, height, text);
    }

    public ButtonTestStart (boolean active, int x, int y, int width, int height) {
        super(active, x, y, width, height);
    }

    public void onClick() {
        Main.setActiveScreen(Main.SCREENS.get(1));
    }
}
