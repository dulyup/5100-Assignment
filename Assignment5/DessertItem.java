package com.assign5;

public abstract class DessertItem {

    private String name;

    public DessertItem() {
        this("");
    }

    public DessertItem(String name) {
        if (name.length() <= DessertShoppe.MAX_ITEM_NAME)
            this.name = name;
        else
            this.name = name.substring(0, DessertShoppe.MAX_ITEM_NAME);
    }

    public abstract int getCost();

    public final String getName() {
        return name;
    }

}
