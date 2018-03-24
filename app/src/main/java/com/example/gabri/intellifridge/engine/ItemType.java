package com.example.gabri.intellifridge.engine;

/**
 * Created by Talksick on 3/24/2018.
 */

public class ItemType {
    public final String name;
    public final ItemCategory category;
    public final NutritionalValue nutritionalValue;
    public final double perishability;

    public ItemType(String name, ItemCategory category, NutritionalValue nutritionalValue,
                    double perishability) {
        this.name = name;
        this.category = category;
        this.nutritionalValue = nutritionalValue;
        this.perishability = perishability;
    }
}
