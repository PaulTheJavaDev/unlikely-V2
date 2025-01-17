import javax.swing.*;
import java.awt.*;

public class UIHandler {

    static int screen_height = 800;
    static int screen_width = 1200;

    //only for testing purposes
    public static void main(String[] args) {
        new UIHandler();
    }

    public UIHandler() {

        JFrame frame = new JFrame("Unlikely V2");
        frame.setSize(screen_width, screen_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(new Color(0x232323));

        JLayeredPane layeredPane = frame.getLayeredPane();

        ImageIcon icon = new ImageIcon("src/images/evilClown.png"); // Update with your image path
        JLabel imageLabel = new JLabel(icon);

        //Calculating the centered position
        int x = (screen_width - icon.getIconWidth()) / 2;
        int y = (screen_height - icon.getIconHeight()) / 2;
        imageLabel.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight()); //icon is centered

        // Add the image label to a lower layer
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        /*
        // Example: Add another component on top
        JButton button = new JButton("Click Me");
        button.setBounds(100, 100, 100, 50); // Position the button
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // Example: Add another component on top
        JButton button1 = new JButton("Click Me");
        button1.setBounds(125, 100, 100, 50); // Position the button
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);
         */

        frame.setVisible(true);
    }

}
