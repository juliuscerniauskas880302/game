package jcerniauskas.videopoker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public class Player {
    /**
     * player's card deck
     */
    private List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
    }

    /**
     * reset player's deck
     */
    public void resetDeck() {
        cards = new ArrayList<>();
    }

    /**
     * Returns card from the deck
     * @param index - card index
     * @return Card object
     */
    public Card getCardAtIndex(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    /**
     * Removes card from the deck
     * @param index - card index
     */
    public void removeCardAtIndex(int index) {
        if (index >= 0 && index < cards.size()) {
            cards.remove(index);
        }
    }

    /**
     * Adds card to the player's deck
     * @param card - Card object
     */
    public void addCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        }
    }

    /**
     * Removes card from the deck
     * @param card - Card object
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Returns players's cards deck
     * @return
     */
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            message.append(i + " : " + cards.get(i).toString()).append("\n");
        }
        return message.toString();
    }
}
