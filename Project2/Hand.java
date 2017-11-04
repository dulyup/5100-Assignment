
package com.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand extends GroupOfCards {
    /**
     * The cardsInHand in the hand
     */
    public final int NUM;
    private int shortest = 0;

    /**
     * make NUM a final instance variable,
     * and initialize it in the Hand constructor with a value equal to the constructor’s first parameter value
     * The second parameter is the maximum number of cardsInHand the player will receive.
     * Use it for the base-constructor call argument.
     *
     * @param playerNum     player’s identification
     * @param numberOfCards maximum number of cardsInHand the player will receive.
     */

    public Hand(int playerNum, int numberOfCards) {
        super(numberOfCards);
        NUM = playerNum;
    }

    /**
     * Use a selection sort strategy for the sort method.
     * Start with unsorted = current size of the array, and step down to unsorted = 1.
     * In each step,
     * ①iterate through the unsorted part of the array,
     * ②find the card having the greatest value of the expression, (13 * suit + number),
     * ③and move this card to the high end of the index range.
     */

    public void sort() throws IndexOutOfBoundsException {
        //cardsInHand in each player's hand
        int size = getCards().size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i; j < size; j++) {
                Card cardI = getCards().get(i);
                Card cardJ = getCards().get(j);
                if (cardI.getNum() + cardI.getSuit() * 13 > cardJ.getNum() + cardJ.getSuit() * 13) {
                    getCards().set(j, cardI);
                    getCards().set(i, cardJ);
                }
            }
        }
    }

    public Card playACard(Game game, Trick trick) throws IndexOutOfBoundsException {
        int index;
        /**
         * If the current size of the trick is zero (you are the first hand),
         * ①let a local integer called index equal the highest card in your shortest suit,
         * ②but if this suit is a void,let index equal the lowest card in any suit.
         */
        if (trick.getWinningCard() == null) {
            index = findLowest(game);
        } else if (trick.getCurrentSize() == 0) {
            index = findHighest(getShortest());
            if (index == -1) {
                index = findLowest(getShortest());
            }
        } else if ((trick.getCurrentSize() == game.PLAYERS - 1)
                && !trick.getHearts() && !trick.getQueen()
                && (index = findLastHigh(trick.getWinningCard().getSuit())) >= 0) {
        } else if ((index = findHighestBelow(trick.getWinningCard())) >= 0) {
        } else if ((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0) {
        } else if ((index = find(12, 3)) >= 0) {
        } // queen of Spades
        else if ((index = find(14, 3)) >= 0) {
        } // Ace of Spades
        else if ((index = find(13, 3)) >= 0) {
        } // King of Spades
        else if ((index = findHighest(2)) >= 0) {
        } // heart
        else {
            index = findHighest();
        }

        Card card = getCards().get(index);
        trick.update(NUM, card);
        game.updateHeartsAndQueen(card);
        getCards().remove(card);
        return card;
    }


    /**
     * @param suit
     * @return the index of the lowest numbered card in the indicated suit.
     */

    public int findLowest(int suit) {
        //If you have no cardsInHand in the suit, return -1.
        if (getCards().size() == 0) {
            return -1;
        }
        List<Integer> valueOfSuitCards = new ArrayList<>();
        for (Card suitCard : getCards()) {
            if (suitCard.getSuit() == suit) {
                valueOfSuitCards.add(suitCard.getNum());
            }
        }
        int min = Collections.min(valueOfSuitCards);
        Card lowestSuitCard = new Card(min, suit);
        return getCards().indexOf(lowestSuitCard);
    }


    /**
     * @param suit
     * @return the number of cardsInHand in the suit indicated by the value of the parameter
     */

    private int findCount(int suit) {
        int count = 0;
        for (Card eachCard : getCards()) {
            if (eachCard.getSuit() == suit) {
                count++;
            }
        }
        return count;
    }

    private int find(int num, int suit) {
        return getCards().indexOf(new Card(num, suit));
    }


    /**
     * @param suit
     * @return the index of the card having the highest numerical value in the suit indicated by the parameter value.
     */

    private int findHighest(int suit) {
        //If you have no cardsInHand in the suit, return -1.
        if (getCards().size() == 0) {
            return -1;
        }

        List<Integer> valueOfSuitCards = new ArrayList<>();

        for (Card suitCard : getCards()) {
            if (suitCard.getSuit() == suit) {
                valueOfSuitCards.add(suitCard.getNum());
            } else {
                return -1;
            }
        }
        int max = Collections.max(valueOfSuitCards);
        Card highestSuitCard = new Card(max, suit);
        return getCards().indexOf(highestSuitCard);
    }

    /**
     * @param game
     * @return the lowest number in your hand, but not a heart until after hearts have been broken
     */

    private int findLowest(Game game) throws IndexOutOfBoundsException {
        int lowestIndex = -1, lowestNum = 15;

        if (getCards().size() == 0) {
            return -1;
        }
        if (!game.getHearts() && findCount(2) == getCurrentSize()) {
            return -1;
        }
        if (game.getHearts()) {
            for (int i = 0; i < getCards().size(); i++) {
                if (getCard(i).getNum() < lowestNum) {
                    lowestIndex = i;
                }
            }
        } else {
            for (int i = 0; i < getCards().size(); i++) {
                if (getCard(i).getNum() < lowestNum && getCard(i).getSuit() != 2) {
                    lowestNum = getCard(i).getNum();
                    lowestIndex = i;
                }
            }
        }
        return lowestIndex;
    }

    /**
     * Return the highest card in the suit
     * If this card is the queen of spades, however, and you have another spade,
     * return the highest card you have below your queen.
     *
     * @param suit
     * @return
     */
    private int findLastHigh(int suit) {
        int index = findHighest(suit);
        Card lastHigh = getCards().get(index);
        if (lastHigh.getNum() == 12 && lastHigh.getSuit() == 3) {
            getCards().remove(lastHigh);
            if (findCount(3) != 0) {
                return findHighest(suit);
            }
            return -1;
        }
        return index;
    }

    /**
     * @param winningCard
     * @return first one with the same suit as winning card and having a number less than the winning card’s number
     */
    private int findHighestBelow(Card winningCard) {
        if (winningCard == null) {
            return -1;
        }
        for (int i = 0; i < getCards().size(); i++) {
            if (getCard(i).getSuit() == winningCard.getSuit() && getCard(i).getNum() < winningCard.getNum()) {
                if (i != getCards().size() - 1 && getCard(i + 1).getSuit() != winningCard.getSuit()) {
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    /**
     * @param game
     * @param suit
     * @return
     */
    private int findMiddleHigh(Game game, int suit) {
        if (suit == 3 && !game.getQueenOfSpades()) {
            //Q of Spades has been played, isQueenOfSpades would be true
            for (Card card : getCards()) {
                if (card.getSuit() == 3 && card.getNum() < 11) {
                    return getCards().indexOf(card);
                }
            }
            return -1;
        }
        return findHighest(suit);
    }

    /**
     * discard the highest remaining card in your hand, regardless of suit.
     *
     * @return the index of the highest remaining card
     */

    private int findHighest() throws IndexOutOfBoundsException {
        if (getCards().size() == 0) {
            return -1;
        }
        int max = 1;
        int index = 0;
        for (int i = 0; i < getCards().size(); i++) {
            if (getCard(i).getNum() > max) {
                max = getCard(i).getNum();
                index = i;
            }
        }
        return index;
    }


    /**
     * Use the setShortest method to determine the best suit to play early in the game, to establish a void as quickly as possible.
     * Start with shortest = clubs.
     * If the number of diamonds is less than or equal to the number of clubs, change shortest to diamonds.
     * If the number of spades is less than or equal to the shorter of those two, and your spades do not include Ace, King, or Queen, change shortest to spades. (Use the find method to see if you have an Ace, King, or Queen.)
     */

    public void setShortest() {

        if (findCount(1) < findCount(0)) {
            shortest = 1;
        }
        if (find(12, 3) == -1 && find(13, 3) == -1 && find(14, 3) == -1) {
            if (findCount(3) <= Math.min(findCount(0), findCount(1))) {
                shortest = 3;
            }
        }
    }

    public int getShortest() {
        return shortest;
    }

    public List<Card> getCardsInHand() {
        return getCards();
    }

    public int getNUM() {
        return NUM;
    }
}

