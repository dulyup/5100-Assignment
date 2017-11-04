package com.project2;

import java.util.List;

public class Deck extends GroupOfCards {

    public static final int TOTAL_CARDS = 52;
    public List<Card> cards = getCards();

    public Deck() {
        super(TOTAL_CARDS);
        int suitNumber = 4;
        int cardNumber = 15;
        for (int i = 0; i < suitNumber; i++) {
            for (int j = 2; j < cardNumber; j++) {
                Card card = new Card(j, i);
                cards.add(card);
            }
        }
    }

    /**
     * to shuffle the deck,
     * ①use a for loop that starts with unshuffled = getCurrentSize and steps down to one.
     * ②In each iteration,use Math.random to pick an index in the unshuffled range,
     * ③remove the card at that index,
     * ④and then add it to the high end of the array.
     */
    public void shuffle() {
        int unshuffled = getCurrentSize();
        for (int i = unshuffled; i > 0; i--) {
            int randomIndex = (int) (Math.random() * unshuffled);
            Card card = cards.get(randomIndex);
            cards.remove(card);
            cards.add(card);
        }
    }

    /**
     * To deal a card, just remove the card at index = 0.
     *
     * @return the dealt card
     */
    public Card dealCard() {
        Card dealCard = cards.get(0);
        cards.remove(dealCard);
        return dealCard;
    }

}
