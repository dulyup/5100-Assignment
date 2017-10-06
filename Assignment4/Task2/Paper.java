package com.assign4;

public class Paper extends Tool implements Rate{
    public Paper(int strength) {
        super(strength);
        setType('p');
    }

    @Override
    public double rate(Tool tool) {
        if (tool.getType() == 'r') return 2;
        else if (tool.getType() == 's') return 0.5;
        else return 1;
    }

    @Override
    public boolean fight(Tool tool) {
        return this.getStrength() * rate(tool) > tool.getStrength();
    }

}
