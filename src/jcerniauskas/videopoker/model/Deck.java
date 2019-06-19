package jcerniauskas.videopoker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public class Deck {

    /**
     * card maximum faces size
     */
    private final static int FACE_SIZE = 13;
    /**
     * card maximum suit size
     */
    private final static int SUIT_SIZE = 4;
    /**
     * cards deck
     */
    private List<Card> deck;

    public Deck() {
        generateNewDeck();
    }

    /**
     * Fill the dek with cards
     */
    public void generateNewDeck() {
        deck = new ArrayList<>();
        for (int i = 0; i < FACE_SIZE; i++) {
            for (int j = 0; j < SUIT_SIZE; j++) {
                Card newCard = new Card(i, j);
                deck.add(newCard);
            }
        }
    }

    /**
     * Shuffle the deck
     */
    public void shuffleTheDeck() {
        Collections.shuffle(this.deck);
    }

    /**
     * Returns card from the deck
     * @param index - card index
     * @return Card object
     */
    public Card getCardAtIndex(int index) {
        if (index < 0 || index > deck.size()) {
            return null;
        }
        return deck.get(index);
    }

    /**
     * Gets random card from the deck, removes card from the deck
     * and returns that card
     * @return Card object
     */
    public Card getRandomCardFromDeck() {
        int min = 0;
        int max = deck.size() - 1;
        int range = max - min + 1;

        int randNumber = (int) (Math.random() * range) + min;
        Card card = deck.get(randNumber);
        deck.remove(card);

        return card;
    }

}
