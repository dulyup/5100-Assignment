package com.assign5;

public class DessertShoppe {

    final static double TAX_RATE = 0.065;
    final static String STORE_NAME = "M & M Dessert Shoppe";
    final static int MAX_ITEM_NAME = 25;
    final static int COST_WIDTH = 12;

    public static String formatCents(int cents) {

        if (cents < 0) {
            cents *= -1;
        }

        double dollars = (double) cents / 100;

        if (dollars < 1 && dollars > 0) {
            String[] dollarsFormat = String.valueOf(dollars).split("\\.");
            return " ." + dollarsFormat[1];
        }
        return String.valueOf(dollars);
    }
}
