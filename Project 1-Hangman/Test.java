package com.project1;

import java.util.ArrayList;

class Test {

    public static void main(String[] args) {
        ArrayList<String> words = new Test().generateWords();
        boolean isExit = false;
        while (!isExit) {
            Hangman hangman = new Hangman(words);
            hangman.playGame();
            isExit = new Test().exit();
        }
    }

    private boolean exit() {
        GetData get = new GetData();
        String entryWord = " ";

        System.out.println("\nPress Y to continue or N to exit.");
        System.out.print("Entry-> ");
        entryWord = get.inputWord();

        if (entryWord.equalsIgnoreCase("Y")) {
            System.out.println("\n\nNew Game Begins!");
            return false;
        } else if (entryWord.equalsIgnoreCase("N")) {
            System.out.println("\n\nGame Over.Thanks!");
            return true;
        }
        return true;
    }


    private ArrayList<String> generateWords() {

        ArrayList<String> words = new ArrayList<>();
        words.add("paradise");
        words.add("gravity");
        words.add("yellow");
        words.add("scientist");
        words.add("magic");
        return words;

    }
}

