package jcerniauskas.videopoker.enums;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public enum Prize {
    ROYAL_FLUSH(800, "Royal Flush"),
    STRAIGHT_FLUSH(50, "Straight Flush"),
    FOUR_KIND(25, "Four of a kind"),
    FULL_HOUSE(9, "Full House"),
    FLUSH(6, "Flush"),
    STRAIGHT(4, "Straight"),
    THREE_KIND(3, "Three of a kind"),
    TWO_PAIR(2, "Two Pair"),
    JACKS_BETTER(1, "Jacks or Better"),
    OTHER(0, "No luck");

    Prize(int amount, String hand) {
        this.amount = amount;
        this.hand = hand;
    }

    private final int amount;
    private final String hand;

    public int getAmount() {
        return amount;
    }

    public String getHand() {
        return hand;
    }
}
