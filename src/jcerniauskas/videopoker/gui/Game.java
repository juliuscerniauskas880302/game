package jcerniauskas.videopoker.gui;

import jcerniauskas.videopoker.core.Ranking;
import jcerniauskas.videopoker.model.Card;
import jcerniauskas.videopoker.model.Deck;
import jcerniauskas.videopoker.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public class Game {

    private Deck deck;
    private Ranking ranking;
    private Player player;

    public Game() {
        deck = new Deck();
        ranking = new Ranking();
        player = new Player();
    }

    /**
     * Adds 5 random cards to the player's deck and shows the main screen
     */
    public void run() {
        deck.shuffleTheDeck();
        for (int i = 0; i < 5; i++) {
            Card card = deck.getRandomCardFromDeck();
            player.addCard(card);
        }
        mainScreen();
    }

    /**
     * Resets the main deck and the player's deck
     */
    private void reset() {
        deck.generateNewDeck();
        deck.shuffleTheDeck();
        player.resetDeck();
        for (int i = 0; i < 5; i++) {
            Card card = deck.getRandomCardFromDeck();
            player.addCard(card);
        }
        mainScreen();
    }

    /**
     * Shows main screen with player's current
     * cards deck and action options
     */
    private void mainScreen() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        String input;
        boolean correctInput = false;
        System.out.println("\n");
        System.out.println("________Player's deck________");
        for (Card c : player.getCards()) {
            System.out.println(c.toString());
        }
        System.out.println("\n___________Actions___________");
        System.out.println("1. Reset the game");
        System.out.println("2. Change cards");
        System.out.println("3. Check score");
        System.out.println("4. Exit");
        System.out.print("Enter your selection: ");

        //loop while input is not correct
        while (!correctInput) {
            input = sc.next();
            try {
                choice = Integer.parseInt(input);
                correctInput = choice > 0 && choice < 5;
            } catch (NumberFormatException ex) {
                System.out.print("Enter numeric value : ");
                continue;
            }
            if (!correctInput) {
                System.out.print("Enter a number from 1 to 4 : ");
            }
        }

        switch (choice) {
            case 1:
                reset();
                break;
            case 2:
                changeCardScreen();
                break;
            case 3:
                scoreScreen();
                break;
            case 4:
                return;
            default:
                break;
        }
    }

    /**
     * Player's swap card screen
     */
    private void changeCardScreen() {
        Scanner sc = new Scanner(System.in);
        String input;
        List<Integer> choices = new ArrayList<>();
        boolean correctInput = false;
        System.out.println("\n");
        System.out.println("________Player's deck________");
        System.out.println(player.toString());
        System.out.println("_____Enter card numbers______");

        // loop while input is not correct
        while (!correctInput) {
            input = sc.nextLine();
            try {
                choices = extractNumbers(input);
                correctInput = true;
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + ". Enter numbers 0 1 ... or 0, 1 ...");
            }
        }
        changePlayersCards(choices);
        scoreScreen();
    }

    /**
     * Shows player's current deck and score from pokers hand
     */
    private void scoreScreen() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        String input;
        boolean correctInput = false;
        ranking.setCards(player.getCards());

        System.out.println("\n");
        System.out.println("Player's score with current deck");
        for (Card c : player.getCards()) {
            System.out.println(c.toString());
        }
        System.out.println();
        System.out.println(ranking.getScore());
        System.out.println("\n___________Actions___________");
        System.out.println("1. Reset the game");
        System.out.println("2. Exit");
        System.out.print("Enter your selection: ");

        while (!correctInput) {
            input = sc.next();
            try {
                choice = Integer.parseInt(input);
                correctInput = choice > 0 && choice < 3;
            } catch (NumberFormatException ex) {
                System.out.print("Enter numeric value : ");
                continue;
            }
            if (!correctInput) {
                System.out.print("Enter a number from 1 to 2 : ");
            }
        }

        switch (choice) {
            case 1:
                reset();
                break;
            case 2:
                return;
            default:
                break;
        }
    }

    /**
     * Finds numbers from string data and returns as integer list
     * @param data - string with numbers
     * @return list of numbers
     */
    private List<Integer> extractNumbers(String data) {
        String[] splitted = data.split("\\s+|,\\s*|\\.\\s*");
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < splitted.length; i++) {
            int num = Integer.parseInt(splitted[i]);
            if (num < 0 && num > 4) throw new NumberFormatException("Numbers should be between 0-4");
            if (!numbers.contains(num)) {
                numbers.add(num);
            }
        }
        return numbers;
    }

    /**
     *  Removes selected cards from player's deck and adds new
     * @param numbers - selected cards numbers
     */
    private void changePlayersCards(List<Integer> numbers){
        for (Integer i: numbers) {
            player.removeCardAtIndex(i.intValue());
        }
        for (int i = 0; i < numbers.size(); i++) {
            player.addCard(deck.getRandomCardFromDeck());
        }
    }

}
