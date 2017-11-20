package com.assign8;

import java.io.*;
import java.util.*;

public class LyricAnalyzer {
    private HashMap<String, ArrayList<Integer>> map;

    public LyricAnalyzer() {
        map = new HashMap<>();
    }

    /**
     * This method will read the lyrics from file and adds to the map.
     *
     * @param file
     */
    public void read(File file) throws IOException {
//        StringBuilder sb = new StringBuilder();
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        int count = 1;
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (i == words.length - 1) {
                    count = -count;
                }
                String wordToUpper = words[i].toUpperCase();
                if (!map.containsKey(wordToUpper)) {
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(count);
                    map.put(wordToUpper, positions);
                } else {
                    map.get(wordToUpper).add(count);
                }
                if (count < 0) {
                    count = -count;
                }
                count++;
            }
        }
        br.close();
        reader.close();
    }

    private void add(String lyricWord, int wordPosition) {
        if (map.containsKey(lyricWord)) {
            map.get(lyricWord).add(wordPosition);
        } else {
            ArrayList<Integer> wordPositions = new ArrayList<>();
            wordPositions.add(wordPosition);
            map.put(lyricWord, wordPositions);
        }
    }

    public void displayWords() {
        System.out.println("Word" + "\t\t" + "Word Position(s)");
        System.out.println("============================");
        List<String> lyricWords = new ArrayList<>(map.keySet());
        Collections.sort(lyricWords);
        for (String lyricWord : lyricWords) {
            System.out.print(lyricWord);
            int spaces = 12 - lyricWord.length();
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(map.get(lyricWord).toString());
        }
        System.out.println("\n");
    }

    public void writeLyrics(File file) throws IOException {
        int total = 0;
        for (String word : map.keySet()) {
            total += map.get(word).size();
        }
        String[] lyric = new String[total + 1];

        for (String word : map.keySet()) {
            ArrayList<Integer> positions = map.get(word);
            for (int i = 0; i < positions.size(); i++) {
                if (positions.get(i) > 0) {
                    lyric[positions.get(i)] = word + " ";
                } else {
                    lyric[-positions.get(i)] = word + "\n";
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        for (int i = 1; i < lyric.length; i++) {
            byte[] bytes = lyric[i].getBytes();
            fos.write(bytes);
        }
        fos.close();
    }

    /**
     * @return the total number of unique words in the lyric by analyzing the map
     */
    public int count() {
        List<String> lyrics = new ArrayList<>(map.keySet());
        return lyrics.size();
    }

    public String mostFrequentWord() {
        int frequency = Integer.MIN_VALUE;
        List<String> result = new ArrayList<>();
        for (String lyricWord : map.keySet()) {
            if (map.get(lyricWord).size() > frequency) {
                frequency = map.get(lyricWord).size();
            }
        }
        for (String lyricWord : map.keySet()) {
            if (map.get(lyricWord).size() == frequency) {
                result.add(lyricWord);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        LyricAnalyzer l = new LyricAnalyzer();
        String filePath = "/Users/lyupingdu/IdeaProjects/JavaAssign/src/com/assign8/Question2_test2.txt";
        File file1 = new File(filePath);
        l.read(file1);
        l.displayWords();
        System.out.println("Unique words: " + l.count());
        System.out.println("Most frequently word: " + l.mostFrequentWord());
        File fileWrite = new File("/Users/lyupingdu/IdeaProjects/JavaAssign/src/com/assign8/Question2_wirte.txt");
        fileWrite.createNewFile();
        l.writeLyrics(fileWrite);

    }
}
