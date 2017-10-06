package com.assign4;

public class Scissors extends Tool implements Rate {

    public Scissors(int strength) {
        super(strength);
        setType('s');
    }

    @Override
    public double rate(Tool tool) {
        if (tool.getType() == 'r') return 0.5;
        else if (tool.getType() == 'p') return 2;
        else return 1;
    }

    @Override
    public boolean fight(Tool tool) {
        return this.getStrength() * rate(tool) > tool.getStrength();
    }
}
