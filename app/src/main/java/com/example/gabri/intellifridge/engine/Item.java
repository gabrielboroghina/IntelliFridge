package com.example.gabri.intellifridge.engine;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Item {
    public final ItemType type;
    public Date expirationDate;

    private boolean isEaten;
    private double quantity; //grams
    public Item(ItemType type, double quantity, Date expirationDate) {
        this.type = type;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.isEaten = false;
    }

    public void eat(double quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) isEaten = true;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCarbs() {
        return quantity * type.nutritionalValue.carbs;
    }

    public double getProteins() {
        return quantity * type.nutritionalValue.proteins;
    }

    public double getFats() {
        return quantity * type.nutritionalValue.fats;
    }

    public boolean isEaten() {
        return isEaten;
    }
}

class CompByExpiration implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        return item1.expirationDate.compareTo(item2.expirationDate);
    }
}
