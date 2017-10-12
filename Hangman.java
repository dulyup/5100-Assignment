package com.project1;

import java.util.ArrayList;

public class Hangman {

    private GetData get = new GetData();
    private ArrayList<String> words;
    private String word; // the chosen word
    private boolean finished = false;
    private int badGuessCount = 0;
    private boolean[] foundLetters;
    private String entryWord = " ";

    public Hangman(ArrayList<String> words) {
        this.words = words;
    }

    //- Starts the game.
    public void playGame() {
        word = chooseWord();
        foundLetters = new boolean[word.length()];
        while (!finished) {
            printHangman();
            displayWord();
            handleGuess();
            if (badGuessCount == 8) {
                gameOver();
            }
        }
    }

    //- handle the guess and add the letter to correctList or WrongList.
    private void handleGuess() {
        System.out.print("\nWhat letter do you guess? ");
        System.out.println("There are " + (8 - badGuessCount) + " chances left.");
        System.out.print("Entry guess -> ");
        entryWord = get.inputWord();

        int count = 0;
        System.out.print("\n");
        boolean goodGuess = displayWord();

        if (goodGuess) {
            System.out.print("\nGuess right! ");
            System.out.println("Press the Enter key to continue!");
            String pause = get.inputWord();
        } else {
            badGuessCount++;
            System.out.print("\nGuess wrong! ");
            System.out.println("Press the Enter key to continue!");
            String pause = get.inputWord();
        }
        for (boolean f : foundLetters) {
            if (f) {
                count++;
            }
        }
        if (count == word.length()) {
            gameWon();
        }

    }

    //- display the correctly guessed letters and hide the remaining with dashes.
    private boolean displayWord() {
        boolean goodGuess = false;
        char ch = entryWord.charAt(0);
        for (int i = 0; i < word.length(); i++)
            if (foundLetters[i]) {
                System.out.print(word.charAt(i) + " ");
            } else if (word.charAt(i) == ch) {
                System.out.print(word.charAt(i) + " ");
                foundLetters[i] = true;
                goodGuess = true;
            } else
                System.out.print("_ ");
        return goodGuess;
    }

    //- Randomly chooses a word from the list.
    private String chooseWord() {
        return words.get((int) (Math.random() * words.size()));
    }

    //- return true if user wins the game.
    private void gameWon() {
        System.out.println("\nYes! You won!");
        System.out.println("The word is: " + word);
        finished = true;
    }

    //- exit the program after the game is over.
    private void gameOver() {
        System.out.print('\u000C');
        printHangman();
        System.out.println("\nSorry, you lost.");
        System.out.println("The word is: " + word);
        finished = true;
    }

    //- print hangman after every guess.
    private void printHangman() {
        if (badGuessCount == 0)
            man_0();
        if (badGuessCount == 1)
            man_1();
        if (badGuessCount == 2)
            man_2();
        if (badGuessCount == 3)
            man_3();
        if (badGuessCount == 4)
            man_4();
        if (badGuessCount == 5)
            man_5();
        if (badGuessCount == 6)
            man_6();
        if (badGuessCount == 7)
            man_7();
        if (badGuessCount == 8)
            completedMan();
        System.out.print("\n");
    }


    private void completedMan() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   -- --");
        System.out.println("|    / \\   ");
        System.out.println("|   /   \\   ");
        System.out.println("|  --   -- ");
    }

    private void man_0() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
    }

    private void man_1() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
        System.out.println("|      ");
    }

    private void man_2() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|       ");
        System.out.println("|       ");
        System.out.println("|       ");
        System.out.println("|       ");
    }

    private void man_3() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   --  ");
        System.out.println("|       ");
        System.out.println("|       ");
        System.out.println("|       ");
    }

    private void man_4() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   -- --");
        System.out.println("|       ");
        System.out.println("|       ");
        System.out.println("|       ");
    }

    private void man_5() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   -- --");
        System.out.println("|    /    ");
        System.out.println("|   /     ");
        System.out.println("|         ");
    }

    private void man_6() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   -- --");
        System.out.println("|    / \\   ");
        System.out.println("|   /   \\   ");
        System.out.println("|            ");
    }

    private void man_7() {
        System.out.println("_______");
        System.out.println("|     |");
        System.out.println("|     O");
        System.out.println("|     | ");
        System.out.println("|   -- --");
        System.out.println("|    / \\   ");
        System.out.println("|   /   \\   ");
        System.out.println("|  --        ");
    }

}
