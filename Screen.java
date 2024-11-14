import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Screen extends GUIComponent{
    private boolean visible = false;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    private String name;
    private ArrayList<Img> imgs;
    private ArrayList<Button> buttons;
    private ArrayList<TextBox> textboxes;
    private ArrayList<Screen> subScreens;

    public Screen(boolean visible, int x, int y, int width, int height, String name, ArrayList<Img> imgs, ArrayList<Button> buttons, ArrayList<TextBox> textboxes, ArrayList<Screen> subScreens) {
        super(visible, x, y, width, height, name);
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.imgs = imgs;
        this.buttons = buttons;
        this.textboxes = textboxes;
        this.subScreens = subScreens;

        loadImages();
    }

    private void loadImages() {
        try {
            for (int i = 0; i < imgs.size(); i++) {                        
                if (new File("img\\" + imgs.get(i).getName() + ".png").exists()) {
                    BufferedImage img = ImageIO.read(new File("img\\" + imgs.get(i).getName() + ".png"));
                                        
                    Image newResizedImage = img.getScaledInstance(imgs.get(i).getWidth(), 
                    imgs.get(i).getHeight(), Image.SCALE_SMOOTH);

                    BufferedImage newImg = new BufferedImage(newResizedImage.getWidth(null), newResizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        
                    Graphics2D graphics2D = newImg.createGraphics();
                    graphics2D.drawImage(newResizedImage, 0, 0, null);
                    graphics2D.dispose();

                    imgs.get(i).setImg(newImg);
                }
            }
            for (int i = 0; i < buttons.size(); i++) {                        
                if (new File("img\\" + buttons.get(i).getName() + ".png").exists()) {
                    BufferedImage img = ImageIO.read(new File("img\\" + buttons.get(i).getName() + ".png"));
                                        
                    Image newResizedImage = img.getScaledInstance(buttons.get(i).getWidth(), 
                    buttons.get(i).getHeight(), Image.SCALE_SMOOTH);
                    
                    BufferedImage newImg = new BufferedImage(newResizedImage.getWidth(null), newResizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        
                    Graphics2D graphics2D = newImg.createGraphics();
                    graphics2D.drawImage(newResizedImage, 0, 0, null);
                    graphics2D.dispose();

                    buttons.get(i).setImg(newImg);
                }
            }

            for (int i = 0; i < subScreens.size(); i++) {                        
                subScreens.get(i).loadImages();
            }
        } catch (IOException e) {
			e.printStackTrace();
		} 
    }

    public void drawGUIComponent(Graphics2D g2d) { // does not print out itself but prints subscreens fine
        if (visible) {            
            for (int i = 0; i < imgs.size(); i++) {
                imgs.get(i).drawGUIComponent(g2d);
            }
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).drawGUIComponent(g2d);
            }
            for (int i = 0; i < textboxes.size(); i++) {
                textboxes.get(i).drawGUIComponent(g2d);
            }
            
            for (int i = 0; i < subScreens.size(); i++) {
                subScreens.get(i).drawGUIComponent(g2d);
            }  
        }
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public ArrayList<Img> getImgs() {
        return imgs;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public ArrayList<TextBox> getTextboxes() {
        return textboxes;
    }

    public ArrayList<Screen> getSubScreens() {
        return subScreens;
    }
}
