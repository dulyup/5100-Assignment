package com.project1;

import java.util.Scanner;

public class GetData {
    private Scanner input;

    public GetData() {
        input = new Scanner(System.in);
    }

    public String inputWord() {
        return input.nextLine();
    }
}
