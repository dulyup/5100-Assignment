package com.project2;

import java.util.ArrayList;
import java.util.List;

public class GroupOfCards {
    private List<Card> cards;
    private int currentSize = 0;

    /**
     * The constructor’s parameter should establish the size of the card array
     *
     * @param currentSize
     */
    public GroupOfCards(int currentSize) {
        cards = new ArrayList();
        this.currentSize = currentSize;
    }
    
    /**
     * The addCard method should
     * 1.increment currentSize after
     * 2.adding the input card to the end of the currently filled part of the cards array.
     *
     * @param input
     * @return
     */
    public void addCard(Card input) {
        cards.add(input);
        currentSize++;
    }

    /**
     * The removeCard method should
     * ①retrieve a reference to the card at index in the cards array,
     * ②decrement the currentSize of the cards array,
     * ③shift all array elements above index down by one place,
     * ④and return the reference to the card originally at index.
     *
     * @param index
     * @return
     */
    public Card removeCard(int index) {
        Card card = cards.get(index);
        cards.remove(card);
        currentSize--;
        return card;
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public List<Card> getCards() {
        return cards;
    }
}
