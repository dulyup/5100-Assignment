package com.assign6;

import java.util.Scanner;

/**
 * @author lyupingdu
 */
public class GetData {

    private Scanner input;

    public GetData(){
        input = new Scanner(System.in);
    }

    public String getInput(){
        return input.next();
    }

    public int getNumber(){
        return input.nextInt();
    }
}
