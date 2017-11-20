package com.assign8;

import java.io.IOException;
import java.util.Scanner;

public class FileCounter {

    private int characterCount, wordCount, lineCount;

    public FileCounter() {

    }

    /**
     * Processes an input source and adds its character, word, and line
     * counts to the respective variables.
     *
     * @param in the scanner to process
     */
    public void read(Scanner in) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.nextLine());
            sb.append(" ");
            lineCount++;
        }

        String content = sb.toString();
        String[] words = content.split(" ");
        wordCount = words.length;

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) != ' ') {
                characterCount++;
            }
        }
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getLineCount() {
        return lineCount;
    }
}
