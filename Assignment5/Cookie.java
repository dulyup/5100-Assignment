package com.assign5;

public class Cookie extends DessertItem {

    int number;
    int pricePerDz;

    public Cookie(String name, int number, int PricePerDz) {
        super(name);
        this.number = number;
        this.pricePerDz = PricePerDz;
    }

    @Override
    public int getCost() {
        return (int) Math.round(pricePerDz * number / 12);
    }

    public int getNumber() {
        return number;
    }

    public int getPricePerDz() {
        return pricePerDz;
    }
}
