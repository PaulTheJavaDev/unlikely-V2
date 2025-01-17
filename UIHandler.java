import javax.swing.*;
import java.awt.*;

public class UIHandler {

    static int screen_height = 800;
    static int screen_width = 1200;

    //for testing purposes there
    public static void main(String[] args) {
        new UIHandler();
    }

    public UIHandler() {

        //frame creation
        JFrame frame = new JFrame("Unlikely V2");
        frame.setSize(screen_width, screen_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        // Create a custom JPanel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("src/images/background.png"); // Update with your background image path
                g.drawImage(backgroundIcon.getImage(), 0, 0, screen_width, screen_height, this);
            }
        };
        backgroundPanel.setLayout(null); // Use absolute layout
        frame.setContentPane(backgroundPanel);

        JLayeredPane layeredPane = frame.getLayeredPane();

        // Load evil clown image
        ImageIcon evilClownIcon = new ImageIcon("src/images/evilClown.png");
        JLabel evilClownIconLabel = new JLabel(evilClownIcon);

        // Calculate centered position for evil clown
        int clownX = (screen_width - evilClownIcon.getIconWidth()) / 2;
        int clownY = (screen_height - evilClownIcon.getIconHeight()) / 2;
        evilClownIconLabel.setBounds(clownX, clownY - 200, evilClownIcon.getIconWidth(), evilClownIcon.getIconHeight());

        // Add evil clown to a higher layer
        layeredPane.add(evilClownIconLabel, JLayeredPane.DEFAULT_LAYER);

        // Load bloody table image
        ImageIcon bloodyTable = new ImageIcon("src/images/bloodyTable1.png");
        JLabel bloodyTableLabel = new JLabel(bloodyTable);

        bloodyTableLabel.setBounds(clownX, clownY + 160, 382, 377);

        // Add the table to a lower layer
        layeredPane.add(bloodyTableLabel, JLayeredPane.PALETTE_LAYER);

        frame.setVisible(true);

        /*
        new stuff to be added:
        -dices and cards
        -dialog + text animations (idk how to do that yet)
        -more evil clown + death screen
        */
    }
}

