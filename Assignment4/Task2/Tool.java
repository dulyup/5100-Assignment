package com.assign4;

public abstract class Tool {

    private int strength;
    private char type;

    public abstract boolean fight(Tool tool);

    public Tool(int strength) {
        this.strength = strength;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int Strength) {
        this.strength = Strength;
    }

}
