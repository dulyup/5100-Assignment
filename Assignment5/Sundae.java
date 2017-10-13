package com.assign5;

public class Sundae extends IceCream {

    String toppingName;
    int toppingCost;


    public Sundae(String icereamName, int icecreamCost, String toppingName, int toppingCost) {
        super(icereamName, icecreamCost);
        this.toppingName = toppingName;
        this.toppingCost = toppingCost;
    }

    public int getCost() {
        return super.getCost() + toppingCost;
    }

    public String getToppingName() {
        return toppingName;
    }

    public int getToppingCost() {
        return toppingCost;
    }
}
