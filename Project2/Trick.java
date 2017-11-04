package com.project2;

public class Trick extends GroupOfCards {
    private int winner;
    private Card winningCard = null;
    private boolean hearts = false;
    private boolean queen = false;

    /**
     * The constructor’s parameter is the number of players,
     * and the constructor calls the superclass’s constructor with one less than twice this number,
     * to allow room in the first trick for undealt cards.
     *
     * @param players
     */
    public Trick(int players) {
        super(players);

    }

    /**
     * if the current card is the winner,
     * ①set winner equal to current player’s number
     * ②and set the winning card equal to the current card.
     * If the current card is a heart, set hearts to true.
     * If the current card is the queen of spades, set queen to true.
     *
     * @param playerNum
     * @param card
     */
    public void update(int playerNum, Card card) {

        if (card == null) {
            return;
        }
        this.addCard(card);

        if (this.isWinner(card)) {
            setWinner(playerNum);
            setWinningCard(card);
        }
        if (card.getSuit() == 2) {
            setHearts(true);
        }
        if (card.getSuit() == 3 && card.getNum() == 12) {
            setQueen(true);
        }

    }

    /**
     * The isWinner method should return true 
     * unless the previous winning card is not null and the current card is not in the suit being played
     * or its number is less than the winning card’s number.
     *
     * @param card
     * @return
     */
    public boolean isWinner(Card card) {
        if (card == null) {
            return false;
        }

        winningCard = getWinningCard();
        if (winningCard == null){
            setWinningCard(card);
        }
        if (winningCard != null && card.getSuit() != winningCard.getSuit()) {
            return false;
        } else if (card.getNum() < winningCard.getNum()) {
            return false;
        }
        return true;
    }

    public int getWinner() {
        return winner;
    }

    public Card getWinningCard() {
        return winningCard;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueen() {
        return queen;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setWinningCard(Card winningCard) {
        this.winningCard = winningCard;
    }

    public void setHearts(boolean hearts) {
        this.hearts = hearts;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }
}