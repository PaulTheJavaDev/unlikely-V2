import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIHandler {

    static final int screen_height = 800;
    static final int screen_width = 1200;

    private boolean cardsDrawn = false; // Tracks if cards have been drawn in the round
    private boolean dicesRolled = false; // Tracks if dices have been rolled in the round

    public static void main(String[] args) {
        new UIHandler();
    }

    public UIHandler() {

        JFrame frame = new JFrame("Unlikely V2");
        frame.setSize(screen_width, screen_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Only true for testing cases

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("src/images/background.png");
                g.drawImage(backgroundIcon.getImage(), 0, 0, screen_width, screen_height, this);
            }
        };
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        JLayeredPane layeredPane = frame.getLayeredPane();
        setupUIElements(layeredPane);

        frame.setVisible(true);
    }

    private void setupUIElements(JLayeredPane layeredPane) {
        // Load evil clown image
        ImageIcon evilClownIcon = new ImageIcon("src/images/evilClown.png");
        JLabel evilClownIconLabel = new JLabel(evilClownIcon);

        int clownX = (screen_width - evilClownIcon.getIconWidth()) / 2;
        int clownY = (screen_height - evilClownIcon.getIconHeight()) / 2;
        evilClownIconLabel.setBounds(clownX, clownY - 200, evilClownIcon.getIconWidth(), evilClownIcon.getIconHeight());

        // Load bloody table image
        JLabel bloodyTableLabel = new JLabel(new ImageIcon("src/images/bloodyTable1.png"));
        bloodyTableLabel.setBounds(clownX, clownY + 160, 382, 377);

        // Setup Dice Icon
        ImageIcon normalIconDice = new ImageIcon("src/images/diceNormal.png");
        ImageIcon hoverIconDice = new ImageIcon("src/images/diceHover.png");
        JLabel diceLabel = new JLabel(normalIconDice);
        diceLabel.setBounds(clownX + 100, clownY + 125, normalIconDice.getIconWidth(), normalIconDice.getIconHeight());

        // Setup Cards Icon
        ImageIcon normalIconCardStack = new ImageIcon("src/images/cardStackNormal.png");
        ImageIcon hoverIconCardStack = new ImageIcon("src/images/cardStackHover.png");
        JLabel cardStackLabel = new JLabel(normalIconCardStack);
        cardStackLabel.setBounds(clownX + 200, clownY + 125, normalIconCardStack.getIconWidth(), normalIconCardStack.getIconHeight());

        // Add a big dice button
        JButton bigDiceButton = new JButton("Roll Big Dice");
        bigDiceButton.setBounds(clownX + 350, clownY + 125, 150, 50);
        bigDiceButton.setEnabled(false); // Initially disabled

        // Add mouse listeners for the cards
        cardStackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!cardsDrawn) {
                    Dice.generateCards();
                    cardsDrawn = true;
                    bigDiceButton.setEnabled(true); // Enable big dice roll
                } else {
                    System.out.println("You have already drawn cards this round!");
                }
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

        // Add mouse listeners for the dice
        diceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (cardsDrawn && !dicesRolled) {
                    Dice.rollDices();
                    dicesRolled = true;
                } else if (!cardsDrawn) {
                    System.out.println("You need to draw cards first!");
                } else {
                    System.out.println("You have already rolled dice this round!");
                }
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

        // Add action listener for the big dice button
        bigDiceButton.addActionListener(e -> {
            Dice.rollBigDice();
            bigDiceButton.setEnabled(false); // Disable after rolling
        });

        // Add a reset button to start the next round
        JButton resetButton = new JButton("Next Round");
        resetButton.setBounds(clownX + 350, clownY + 200, 150, 50);
        resetButton.addActionListener(e -> {
            cardsDrawn = false;
            dicesRolled = false;
            Dice.resetRerolls();
            bigDiceButton.setEnabled(false);
            System.out.println("New round started!");
        });

        // Adding elements with Z-index
        layeredPane.add(evilClownIconLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(bloodyTableLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(diceLabel, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.add(cardStackLabel, JLayeredPane.PALETTE_LAYER, 1);
        layeredPane.add(bigDiceButton, JLayeredPane.PALETTE_LAYER, 2);
        layeredPane.add(resetButton, JLayeredPane.PALETTE_LAYER, 3);
    }
}
