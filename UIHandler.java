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
        /*
        JLabel diceLabel = createInteractiveLabel(
                "src/images/diceNormal.png",
                "src/images/diceHover.png",
                clownX + 40, clownY + 125,
                "Dice clicked");
         */

        //Setup Dice Icon
        ImageIcon normalIconDice = new ImageIcon("src/images/diceNormal.png");
        ImageIcon hoverIconDice = new ImageIcon("src/images/diceHover.png");
        JLabel diceLabel = new JLabel(normalIconDice);
        diceLabel.setBounds(clownX + 100, clownY + 125, normalIconDice.getIconWidth(), normalIconDice.getIconHeight());

        //Setup Cards Icon
        ImageIcon normalIconCardStack = new ImageIcon("src/images/cardStackNormal.png");
        ImageIcon hoverIconCardStack = new ImageIcon("src/images/cardStackHover.png");
        JLabel cardStackLabel = new JLabel(normalIconCardStack);
        cardStackLabel.setBounds(clownX + 200, clownY + 125, normalIconCardStack.getIconWidth(), normalIconCardStack.getIconHeight());

        //Adding MouseListener to
        cardStackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("debugMessage");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cardStackLabel.setIcon(hoverIconCardStack);
                cardStackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cardStackLabel.setIcon(normalIconCardStack);
                cardStackLabel.setCursor(Cursor.getDefaultCursor());
            }
        });

        diceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("debugMessage of the Dice");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                diceLabel.setIcon(hoverIconDice);
                diceLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                diceLabel.setIcon(normalIconDice);
                diceLabel.setCursor(Cursor.getDefaultCursor());
            }
        });

        //Adding elements with Z-index
        layeredPane.add(evilClownIconLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(bloodyTableLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(diceLabel, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.add(cardStackLabel, JLayeredPane.PALETTE_LAYER, 1);
    }
}
