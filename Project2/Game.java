package com.project2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    final int PLAYERS;
    private Deck deck;
    private Hand[] players;
    private Trick[] tricks;
    private int numberOfTricks = 0;
    private boolean hearts = false;
    private boolean queenOfSpades = false;
    private List<Card> cards;
    private int trickNumber;

    /**
     * Composition
     */
    public Game(int numOfPlayers) {
        PLAYERS = numOfPlayers;
        deck = new Deck();
        int numberOfCards = deck.getCurrentSize() / numOfPlayers;
        players = new Hand[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new Hand(i, numberOfCards);
        }
        tricks = new Trick[numberOfCards];
    }

    public void playAGame() {
        deck.shuffle();
        cards = deck.getCards();
        int sizeOfLeftCards = cards.size();

        for (int j = 0; j < tricks.length; j++) {
            for (int i = 0; i < PLAYERS; i++) {
                Card randomCard = cards.get((int) (Math.random() * (sizeOfLeftCards)));
                players[i].addCard(randomCard);
                cards.remove(randomCard);
                sizeOfLeftCards--;

            }
        }

        List<Card> leftCards = new ArrayList<>();
        leftCards.addAll(cards);

        int playNum = 0;
        for (int i = 0; i < PLAYERS; i++) {
            players[i].sort();
            players[i].setShortest();
            System.out.print("    Player " + i);
            System.out.println(" shortest = " + players[i].getShortest());
            for (Card card : players[i].getCardsInHand()) {
                card.display();
                if (card.getNum() == 2 && card.getSuit() == 0) {
                    playNum = i;
                }
            }
        }
        System.out.println("\n");

        Card card;
        for (trickNumber = 0; trickNumber < tricks.length; trickNumber++) {
            tricks[trickNumber] = new Trick(PLAYERS);
            numberOfTricks++;
            if (trickNumber == 0) {
                card = new Card(2, 0);
            } else {
                playNum = tricks[trickNumber - 1].getWinner();
                card = players[playNum].playACard(this, tricks[trickNumber]);
            }
            System.out.print("Player " + playNum + "\t\t\t");
            card.display();

            for (int i = 1; i < PLAYERS; i++) {
                playNum = (playNum + 1) % PLAYERS;
                card = players[playNum].playACard(this, tricks[trickNumber]);
                System.out.print("Player " + playNum + "\t\t\t");
                card.display();
            }

            if (trickNumber == 0) {
                for (Card leftCard : leftCards) {
                    System.out.print("Undealt Card " + "\t\t");
                    leftCard.display();
                }
            }
            System.out.println("");
        }

        for (int i = 0; i < PLAYERS; i++) {
            int points = computePoints(i);
            System.out.println("Player " + i + "  Score = " + points);
        }
        System.out.println("Play another game (y / n)?");
    }

    public void updateHeartsAndQueen(Card card) {
        if (card.getSuit() == 2 && !hearts) {
            System.out.println("Heart is now broken");
            hearts = true;
        }
        if (card.getSuit() == 3 && card.getNum() == 12) {
            queenOfSpades = true;
        }
    }

    private int computePoints(int playerNum) throws IndexOutOfBoundsException {
        int points = 0;
        for (int i = 0; i < numberOfTricks - 1; i++) {
            if (tricks[i].getWinner() == playerNum) {
                for (int j = 0; j < tricks[i].getCards().size(); j++) {
                    if (tricks[i].getCard(j).getSuit() == 2) {
                        points++;
                    }
                    if (tricks[i].getCard(j).getSuit() == 3 && tricks[i].getCard(j).getNum() == 12) {
                        points += 13;
                    }
                }
            }
        }
        return points;
    }

    public int getNumberOfTricks() {
        return numberOfTricks;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueenOfSpades() {
        return queenOfSpades;
    }

}

