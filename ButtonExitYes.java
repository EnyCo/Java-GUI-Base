public class ButtonExitYes extends GUIComponent {
    public ButtonExitYes (boolean active, int x, int y, int width, int height, String text) {
        super(active, x, y, width, height, text);
    }

    public ButtonExitYes (boolean active, int x, int y, int width, int height) {
        super(active, x, y, width, height);
    }

    public void onClick() {
        Main.setGameOver(true);
    }
}
