package jcerniauskas.videopoker.model;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public class Card {

    /**
     * card suit
     */
    private int suit;
    /**
     * card rank
     */
    private int rank;

    /**
     * list of available card suits
     */
    private String[] suits = {"HEARTS", "DIAMOND", "CLUBS", "SPADES"};
    /**
     * list of available card ranks
     */
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE"};

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Returns formatted card's name string
     * @return card name string
     */
    public String getCardName() {
        return ranks[this.rank] + " of " + suits[this.suit];
    }

    @Override
    public String toString() {
        return "Card{" + ranks[this.rank] + " of " + suits[this.suit] +
                '}';
    }
}
