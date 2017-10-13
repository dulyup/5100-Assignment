package com.assign5;

import java.util.Vector;

public class Checkout {
    private int totalCost;
    private Vector<DessertItem> dessertItems;

    //vector of dessertItems
    public Checkout() {
        dessertItems = new Vector();
    }

    public int numberOfItems() {
        return dessertItems.size();
    }

    public void enterItem(DessertItem item) {
        dessertItems.add(item);
    }

    public void clear() {
        dessertItems.clear();
    }

    //Total cost without tax
    public int totalCost() {
        totalCost = 0;
        for (DessertItem item : dessertItems
                ) {
            totalCost += item.getCost();
        }
        return totalCost;
    }

    public int totalTax() {
        return (int) Math.round(DessertShoppe.TAX_RATE * totalCost);
    }

    public String toString() {
        String receipt = "";
        String dashes = "";
        String leftColumn = "%-" + DessertShoppe.MAX_ITEM_NAME + "s";
        String rightColumn = "%" + DessertShoppe.COST_WIDTH + "s";
        int receiptWidth = DessertShoppe.MAX_ITEM_NAME + DessertShoppe.COST_WIDTH;

        //print store name & dashes
        String storeName = DessertShoppe.STORE_NAME;
        for (int i = 0; i < storeName.length(); i++) {
            dashes += "-";
        }
        String padding = "%" + (receiptWidth - storeName.length()) / 2 + "s";
        receipt += String.format(padding + "%s\n" + padding + "%s\n\n", "", storeName, "", dashes);//center the shoppeName

        //print unit price &  quantity
        for (int i = 0; i < dessertItems.size(); i++) {
            String itemName = dessertItems.get(i).getName();
            String itemCost = DessertShoppe.formatCents(dessertItems.get(i).getCost());
            if (dessertItems.get(i) instanceof Candy) {
                double weight = ((Candy) dessertItems.get(i)).getWeight();
                String cost = DessertShoppe.formatCents(((Candy) dessertItems.get(i)).getPricePerLb());
                receipt += String.format("%,.2f lbs. @ %s /lb.\n", weight, cost);//2 decimal places of precision
            }
            if (dessertItems.get(i) instanceof Cookie) {
                int number = ((Cookie) dessertItems.get(i)).getNumber();
                String cost = DessertShoppe.formatCents(((Cookie) dessertItems.get(i)).getPricePerDz());
                receipt += String.format("%d @ %s /dz.\n", number, cost);
            }
            if (dessertItems.get(i) instanceof Sundae) {
                String toppingName = ((Sundae) dessertItems.get(i)).getToppingName();
                receipt += String.format("%s Sundae with\n", toppingName);
            }
            // print item names and its cost
            receipt += String.format(leftColumn + rightColumn + "\n", itemName, itemCost);
        }

        //Tax
        String tax = DessertShoppe.formatCents(totalTax());
        String[] taxes = tax.split("\\.");
        receipt += String.format("\n" + leftColumn + rightColumn + "\n", "Tax", "."+taxes[1]);

        //Cost + tax
        String total = DessertShoppe.formatCents(totalCost() + totalTax());
        receipt += String.format(leftColumn + rightColumn + "\n", "Total Cost", total);

        return receipt;
    }

}
