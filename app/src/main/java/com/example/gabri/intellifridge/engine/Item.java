package com.example.gabri.intellifridge.engine;

import java.util.Date;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Item {
    public final ItemType type;
    public Date date;

    private boolean isEaten;
    private double quantity; //kilos
    public Item(ItemType type, double quantity, Date date) {
        this.type = type;
        this.quantity = quantity;
        this.date = date;
        this.isEaten = false;
    }

    public void eat(double quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) isEaten = true;
    }

    public boolean isEaten() {
        return isEaten;
    }
}
