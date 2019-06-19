package jcerniauskas.videopoker.core;

import jcerniauskas.videopoker.enums.Prize;
import jcerniauskas.videopoker.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcerniauskas
 * @since 2019-06-19
 */
public class Ranking {

    /**
     * Player's cards deck
     */
    private List<Card> cards;

    public Ranking() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Sort cards by rank
     */
    private void sortByRank() {
        int size = cards.size();

        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (cards.get(j).getRank() < cards.get(min).getRank()) {
                    min = j;
                }
            }
            int temp = cards.get(min).getRank();
            cards.get(min).setRank(cards.get(i).getRank());
            cards.get(i).setRank(temp);
        }
    }

    /**
     * Sort cards by suit
     */
    private void sortBySuit() {
        int size = cards.size();

        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (cards.get(j).getSuit() < cards.get(min).getSuit()) {
                    min = j;
                }
            }

            int temp = cards.get(min).getSuit();
            cards.get(min).setSuit(cards.get(i).getSuit());
            cards.get(i).setSuit(temp);
        }
    }

    /**
     * HAND RANKINGS
     * 0 - HEARTS, 1 - DIAMONDS, 2 - CLUBS, 3 - SPADES
     * 0-'2', 1-'3', 2-'4', 3-'5', 4-'6', 5-'7', 6-'8', 7-'9', 8-'10', 9-'JACK', 10-'QUEEN', 11-'KING', 12-'ACE'
     */

    /**
     * Check if cards deck is Royal Flush
     * Royal flush - A, K, Q, J, 10, all the same suit.
     * @return true if Royal Flush
     */
    private boolean isRoyalFlush() {
        boolean p1, p2;

        sortBySuit();

        // check if suit is the same
        p1 = cards.get(0).getSuit() == cards.get(4).getSuit();

        sortByRank();

        // check for pattern A K Q J 10
        p2 = cards.get(0).getRank() == 8 &&
                cards.get(1).getRank() == 9 &&
                cards.get(2).getRank() == 10 &&
                cards.get(3).getRank() == 11 &&
                cards.get(4).getRank() == 12;

        return (p1 || p2);
    }

    /**
     * Check if cards deck is Straight flush
     * Straight flush - Five cards in a sequence, all in the same suit.
     * @return true if Straight flush
     */
    private boolean isStraightFlush() {
       boolean p1, p2;

       p1 = isSequential();
       sortBySuit();
       p2 = cards.get(0).getSuit() == cards.get(4).getSuit();

       return (p1 && p2);
    }

    /**
     * Check if cards deck is Four of a kind
     * Four of a kind - All four cards of the same rank.
     * @return true if Four of a kind
     */
    private boolean isFourKind() {
        boolean p1, p2;

        sortByRank();

        // check for pattern a a a a b
        p1 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank();
        // check for pattern b a a a a
        p2 = cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank();

        return (p1 || p2);
    }

    /**
     * Check if cards deck is Full House
     * Full House - Three of a kind with a pair.
     * @return true if Full House
     */
    private boolean isFullHouse() {
        boolean p1, p2;

        sortByRank();

        // check for pattern a a a b b
        p1 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank();
        // check for pattern b b a a a
        p2 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank();

        return (p1 || p2);
    }

    /**
     * Check if cards deck is Flush
     * Flush - Any five cards of the same suit, but not in a sequence.
     * @return true if Flush
     */
    private boolean isFlush() {
        boolean p1, p2;

        sortBySuit();

        // check if cards have same suit
        p1 = cards.get(0).getSuit() == cards.get(4).getSuit();

        sortByRank();

        // check if cards in a sequence by increasing value
        p2 = isSequential();

        return (p1 && !p2);
    }

    /**
     * Check if cards deck is Straight
     * Straight - Five cards in a sequence, but not of the same suit.
     * @return true if Straight
     */
    private boolean isStraight() {
        boolean p1, p2, p3, p4, p5;

        sortByRank();

        if (cards.get(4).getRank() == 12) {
            // check for patter 2 3 4 5 ACE
            p1 = cards.get(0).getRank() == 0 && cards.get(1).getRank() == 1 &&
                    cards.get(2).getRank() == 2 && cards.get(3).getRank() == 3;
            // check for pattern 10 JACK QUEEN KING ACE
            p2 = cards.get(0).getRank() == 8 && cards.get(1).getRank() == 9 &&
                    cards.get(2).getRank() == 10 && cards.get(3).getRank() == 11;

            sortBySuit();
            // check if suit the same
            p3 = cards.get(0).getSuit() == cards.get(4).getSuit();

            return (!p3 && (p1 || p2));
        } else {
            // check if cards in a sequence
            p4 = isSequential();
            sortBySuit();
            // check if suit the same
            p5 = cards.get(0).getSuit() == cards.get(4).getSuit();
            return (p4 && !p5);
        }

    }

    /**
     * Check if cards deck is Three of a kind
     * Three of a kind - Three cards of the same rank.
     * @return true if Three of a kind
     */
    private boolean isThreeKind() {
        boolean p1, p2, p3;

        sortByRank();

        // check for pattern a a a b c
        p1 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(3).getRank() != cards.get(4).getRank() &&
                cards.get(3).getRank() != cards.get(0).getRank() &&
                cards.get(4).getRank() != cards.get(0).getRank();
        // check for pattern b a a a c
        p2 = cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank() &&
                cards.get(0).getRank() != cards.get(1).getRank() &&
                cards.get(4).getRank() != cards.get(1).getRank() &&
                cards.get(0).getRank() != cards.get(4).getRank();
        // check for pattern b c a a a
        p3 = cards.get(2).getRank() == cards.get(3).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank() &&
                cards.get(0).getRank() != cards.get(1).getRank() &&
                cards.get(0).getRank() != cards.get(2).getRank() &&
                cards.get(1).getRank() != cards.get(2).getRank();

        return (p1 || p2 || p3);
    }

    /**
     * Check if cards deck is Two pair
     * Two pair - Two different pairs.
     * @return true if Two pair
     */
    private boolean isTwoPair() {
        boolean p1, p2, p3;

        sortByRank();

        // check for pattern a a b b c
        p1 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank();
        // check for pattern c a a b b
        p2 = cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank();
        // check for pattern a a c b b
        p3 = cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank();

        return (p1 || p2 || p3);
    }

    /**
     * Check if cards deck is Jacks or Better
     * Jacks or Better - Pair of Jacks
     * @return true if Jacks or Better
     */
    private boolean isJacksOrBetter() {
        boolean p1, p2, p3, p4;

        sortByRank();

        // check for pattern a a b c d
        p1 = cards.get(0).getRank() == 9 && cards.get(1).getRank() == 9;
        // check for pattern b a a c d
        p2 = cards.get(1).getRank() == 9 && cards.get(2).getRank() == 9;
        // check for pattern b c a a d
        p3 = cards.get(2).getRank() == 9 && cards.get(3).getRank() == 9;
        // check for pattern b c d a a
        p4 = cards.get(3).getRank() == 9 && cards.get(4).getRank() == 9;

        return (p1 || p2 || p3 || p4);
    }

    /**
     * Check if cards of sequential rank
     * @return true if cards goes in sequence
     */
    private boolean isSequential() {
        if (cards == null) {
            return false;
        }

        sortByRank();

        int cardRank = cards.get(0).getRank() + 1;
        for (int i = 1; i < 5; i++) {
            if (cards.get(i).getRank() != cardRank) {
                return false;
            }
            cardRank++;
        }
        return true;
    }

    /**
     * Calculate the score and return as string message
     * @return string message
     */
    public String getScore() {
        if (cards == null || cards.size() != 5) {
            return "Wrong card deck";
        }

        String message;

        if (isRoyalFlush()) {
            message = Prize.ROYAL_FLUSH.getHand() + " and you won - " + Prize.ROYAL_FLUSH.getAmount();
        } else if (isStraightFlush()) {
            message = Prize.STRAIGHT_FLUSH.getHand() + " and you won - " + Prize.STRAIGHT_FLUSH.getAmount();
        } else if (isFourKind()) {
            message = Prize.FOUR_KIND.getHand() + " and you won - " + Prize.FOUR_KIND.getAmount();
        } else if (isFullHouse()) {
            message = Prize.FULL_HOUSE.getHand() + " and you won - " + Prize.FULL_HOUSE.getAmount();
        } else if (isFlush()) {
            message = Prize.FLUSH.getHand() + " and you won - " + Prize.FLUSH.getAmount();
        } else if (isStraight()) {
            message = Prize.STRAIGHT.getHand() + " and you won - " + Prize.STRAIGHT.getAmount();
        } else if (isThreeKind()) {
            message = Prize.THREE_KIND.getHand() + " and you won - " + Prize.THREE_KIND.getAmount();
        } else if (isTwoPair()) {
            message = Prize.TWO_PAIR.getHand() + " and you won - " + Prize.TWO_PAIR.getAmount();
        } else if (isJacksOrBetter()) {
            message = Prize.JACKS_BETTER.getHand() + " and you won - " + Prize.JACKS_BETTER.getAmount();
        } else {
            message = Prize.OTHER.getHand() + " and you won - " + Prize.OTHER.getAmount();
        }
        return message;
    }

}
