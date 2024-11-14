public class ButtonAnswer extends Button {
    private int value = 0;
    public ButtonAnswer (boolean visible, int x, int y, int width, int height, String name, int value) {
        super(visible, x, y, width, height, name);
    }

    public int getValue(){
        return value;
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE THESE TWO BUTTONS
        try {
            Main.getActiveScreen().getButtons().get(2).setVisible(true);
            Main.getActiveScreen().getImgs().get(3).setVisible(true);

            //Main.score++;
        } catch (Exception e) {
            System.out.println("missing default buttons answer");
        }
    }
}
