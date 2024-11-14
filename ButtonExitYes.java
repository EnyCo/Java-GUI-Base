public class ButtonExitYes extends Button {
    public ButtonExitYes (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
    }

    public void onClick() {
        Main.setGameOver(true);
    }
}
