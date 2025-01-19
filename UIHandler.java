import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIHandler {

    static final int screen_height = 800;
    static final int screen_width = 1200;

    public static void main(String[] args) {
        new UIHandler();
    }

    public UIHandler() {

        JFrame frame = new JFrame("Unlikely V2");
        frame.setSize(screen_width, screen_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Only true for testing cases

        //Create a custom JPanel for the background (I definitely wrote this)
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("src/images/background.png"); //Update with your background image path
                g.drawImage(backgroundIcon.getImage(), 0, 0, screen_width, screen_height, this);
            }
        };
        backgroundPanel.setLayout(null); //No Layout Manager for positioning things on my own
        frame.setContentPane(backgroundPanel);

        JLayeredPane layeredPane = frame.getLayeredPane();

        //Load and position the UI elements
        setupUIElements(layeredPane);

        frame.setVisible(true);
    }

    private void setupUIElements(JLayeredPane layeredPane) {
        //Load evil clown image
        ImageIcon evilClownIcon = new ImageIcon("src/images/evilClown.png");
        JLabel evilClownIconLabel = new JLabel(evilClownIcon);

        int clownX = (screen_width - evilClownIcon.getIconWidth()) / 2;
        int clownY = (screen_height - evilClownIcon.getIconHeight()) / 2;
        evilClownIconLabel.setBounds(clownX, clownY - 200, evilClownIcon.getIconWidth(), evilClownIcon.getIconHeight());

        //Load bloody table image
        JLabel bloodyTableLabel = new JLabel(new ImageIcon("src/images/bloodyTable1.png"));
        bloodyTableLabel.setBounds(clownX, clownY + 160, 382, 377);

        //Load dice image
        JLabel diceLabel = createInteractiveLabel(
                "src/images/diceNormal.png",
                "src/images/diceHover.png",
                clownX + 40, clownY + 125,
                "Dice clicked");

        //Load card stack image
        JLabel cardStackLabel = createInteractiveLabel(
                "src/images/cardStackNormal.png",
                "src/images/cardStackHover.png",
                clownX + 200, clownY + 125,
                "Cards clicked");

        //Adding elements with Z-index
        layeredPane.add(evilClownIconLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(bloodyTableLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(diceLabel, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.add(cardStackLabel, JLayeredPane.PALETTE_LAYER, 1);
    }

    private JLabel createInteractiveLabel(String normalImagePath, String hoverImagePath, int x, int y, String debugMessage) {
        // Load icons
        ImageIcon normalIcon = new ImageIcon(normalImagePath);
        ImageIcon hoverIcon = new ImageIcon(hoverImagePath);

        // Create JLabel
        JLabel label = new JLabel(normalIcon);
        label.setBounds(x, y, normalIcon.getIconWidth(), normalIcon.getIconHeight());

        // Add MouseListener
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(debugMessage);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(hoverIcon);
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(normalIcon);
                label.setCursor(Cursor.getDefaultCursor());
            }
        });

        return label;
    }
}
