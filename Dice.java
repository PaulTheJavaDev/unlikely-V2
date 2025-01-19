import java.util.ArrayList;
import java.util.Random;

public class Dice {

    private static final Random random = new Random();
    private static ArrayList<Integer> generatedCards = new ArrayList<>();
    private static ArrayList<Integer> rolledDices = new ArrayList<>();
    private static int points = 0;
    private static int rerollCount = 1; // Reroll counter for the dice
    private static int bigDiceRerollCount = 1; // Reroll counter for the big dice
    private static int drawableCards = 9; // Cards left to draw in the game

    /**
     * Generates 3 random cards and reduces drawable cards by 1.
     */
    public static void generateCards() {
        generatedCards.clear();
        for (int i = 0; i < 3; i++) {
            int card = random.nextInt(6) + 1; // Range 1-6
            generatedCards.add(card);
        }
        System.out.println("Generated cards: " + generatedCards);
        drawableCards--;
    }

    /**
     * Rolls 3 dice and stores their values.
     */
    public static void rollDices() {
        if (rolledDices.isEmpty()) {
            rolledDices.clear();
            for (int i = 0; i < 3; i++) {
                int dice = random.nextInt(6) + 1; // Range 1-6
                rolledDices.add(dice);
            }
            System.out.println("Rolled dice: " + rolledDices);
        } else {
            System.out.println("You already rolled dice this round!");
        }
    }

    /**
     * Rerolls all dice if reroll is available.
     */
    public static void rerollDices() {
        if (rerollCount > 0 && !rolledDices.isEmpty()) {
            rolledDices.clear();
            for (int i = 0; i < 3; i++) {
                int dice = random.nextInt(6) + 1; // Range 1-6
                rolledDices.add(dice);
            }
            rerollCount--;
            System.out.println("Rerolled dice: " + rolledDices);
        } else {
            System.out.println("You cannot reroll dice this round!");
        }
    }

    /**
     * Compares rolled dice to generated cards and awards points.
     */
    public static void checkCards() {
        int matches = 0;
        for (int i = 0; i < rolledDices.size(); i++) {
            if (generatedCards.contains(rolledDices.get(i))) {
                matches++;
                generatedCards.remove(Integer.valueOf(rolledDices.get(i))); // Prevent duplicate matches
            }
        }

        points += matches;
        System.out.println("You matched " + matches + " cards. Total points: " + points);
    }

    /**
     * Rolls the big dice for random effects.
     */
    public static void rollBigDice() {
        if (bigDiceRerollCount > 0) {
            System.out.println("Rolling the big dice...");
            int roll = random.nextInt(6) + 1; // Range 1-6
            switch (roll) {
                case 1 -> {
                    points--;
                    System.out.println("You lost 1 point! Total points: " + points);
                }
                case 2 -> {
                    rerollCount--;
                    System.out.println("You lost 1 reroll! Rerolls left: " + rerollCount);
                }
                case 3 -> System.out.println("Nothing happens.");
                case 4 -> System.out.println("Nothing happens.");
                case 5 -> {
                    rerollCount++;
                    System.out.println("You gained 1 reroll! Rerolls left: " + rerollCount);
                }
                case 6 -> {
                    points++;
                    System.out.println("You gained 1 point! Total points: " + points);
                }
            }
            bigDiceRerollCount--;
        } else {
            System.out.println("You cannot roll the big dice this round!");
        }
    }

    /**
     * Resets the reroll counters for the next round.
     */
    public static void resetRerolls() {
        rerollCount = 1;
        bigDiceRerollCount = 1;
        System.out.println("Reroll counters have been reset for the next round.");
    }
}
