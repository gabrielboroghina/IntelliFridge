package com.example.gabri.intellifridge.engine;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Fridge {
    private SortedSet<Item> items; //Sorted by expiration date

    public Fridge() {
        items = new TreeSet<Item>(new CompByExpiration());
    }

    public SortedSet<Item> getItems() {
        return items;
    }
}
