package com.assign4;

public class Rock extends Tool implements Rate {

    public Rock(int strength) {
        super(strength);
        setType('r');
    }

    @Override
    public double rate(Tool tool) {
        if (tool.getType() == 's') return 2;
        else if (tool.getType() == 'p') return 0.5;
        else return 1;
    }

    @Override
    public boolean fight(Tool tool) {
        return this.getStrength() * rate(tool) > tool.getStrength();
    }

}
