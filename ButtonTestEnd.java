public class ButtonTestEnd extends GUIComponent {
    public ButtonTestEnd (boolean active, int x, int y, int width, int height, String text) {
        super(active, x, y, width, height, text);
    }

    public ButtonTestEnd (boolean active, int x, int y, int width, int height) {
        super(active, x, y, width, height);
    }

    public void onClick() {
        Main.setActiveScreen(Main.SCREENS.get(0));
    }
}
