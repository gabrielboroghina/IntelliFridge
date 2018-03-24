package com.example.gabri.intellifridge.engine;

import android.util.Log;

/**
 * Created by Talksick on 3/24/2018.
 */

public class NutritionalValue {
    public final double proteins, carbs, fats;
    public NutritionalValue(double carbs, double proteins, double fats) {
        if (proteins + carbs + fats > 1) {
            proteins /= 100;
            carbs /= 100;
            fats /= 100;
        }
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public static double computeCalories(double carbs, double proteins, double fats) {
        return (proteins + carbs) * 4 + fats * 9;
    }

    public double getCalories() {
        return computeCalories(carbs, proteins, fats);
    }
}
