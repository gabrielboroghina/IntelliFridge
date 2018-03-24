package com.example.gabri.intellifridge.engine;

/**
 * Created by Talksick on 3/24/2018.
 */

public class NutritionalValue {
    public final double proteins, carbs, fats;
    public NutritionalValue(double proteins, double carbs, double fats) {
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public static double computeCalories(double carbs, double proteins, double fats) {
        return (proteins + carbs) * 4 + fats;
    }

    public double getCalories() {
        return computeCalories(carbs, proteins, fats);
    }
}
