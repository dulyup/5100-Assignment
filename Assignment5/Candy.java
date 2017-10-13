package com.assign5;

public class Candy extends DessertItem {


    private double weight;
    private int pricePerLb;// the unit is cents

    public Candy(String name, double weight, int PricePerLb) {
        super(name);
        this.weight = weight;
        this.pricePerLb = PricePerLb;
    }

    @Override
    public int getCost() {
        return (int)Math.round(pricePerLb * weight);
    }

    public double getWeight() {
        return weight;
    }

    public int getPricePerLb() {
        return pricePerLb;
    }
}
