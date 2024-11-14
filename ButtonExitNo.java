public class ButtonExitNo extends Button {
    public ButtonExitNo (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE THESE TWO BUTTONS
        try {
            Main.popVisibleScreens();
            //Main.popVisibleScreens();
            //Main.pushVisibleScreens(Main.getScreens().get("Question 1"));//get stack second highest -> name +1
        } catch (Exception e) {
            //System.out.println("missing default buttons");
        }
    }
}
