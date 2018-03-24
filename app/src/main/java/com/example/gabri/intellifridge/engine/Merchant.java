package com.example.gabri.intellifridge.engine;

import android.support.annotation.NonNull;

import com.example.gabri.intellifridge.engine.NutritionalValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Merchant {

    private static final double A_DECAY = 1.0;
    private static final double B_DECAY = 0.0;
    private static final double A_DESIRE = 1.0;
    private static final double B_DESIRE = 0.0;
    private static final double A_NATURE = 1.0;
    private static final double B_NATURE = 0.0;
    private static final double A_AVAILABILITY = 0.0;
    private static final double B_AVAILABILITY = 1.0;

    private static double computeAvailabilityFactor(ItemType type) {
        return 1.0;
    }
    private static double computeDecayFactor(ItemType type, int n_days) {
        double tmp = type.perishability - n_days * 0.25;
        if (tmp < 0) tmp = 0.0;
        tmp /= n_days * 0.75;
        if (tmp > 1) tmp = 1.0;
        return tmp;
    }

    private static double computeDesireFactor(ItemType type, UserPreferences preferences) {
        double tmp = ((double)preferences.getGradeByType(type)) / UserPreferences.MAX_GRADE;
        return tmp;
    }

    private static double computeNatureFactor(ItemType type, UserPreferences preferences,
                                       double relative_carbs_need, double relative_proteins_need,
                                       double relative_fats_need) {
        double carbs_richness = type.nutritionalValue.carbs / preferences.getCarbsPerDay();
        double proteins_richness = type.nutritionalValue.proteins / preferences.getProteinsPerDay();
        double fats_richness = type.nutritionalValue.fats / preferences.getFatsPerDay();
        double total = carbs_richness + proteins_richness + fats_richness;
        carbs_richness /= total;
        proteins_richness /= total;
        fats_richness /= total;
        double ans = carbs_richness * relative_carbs_need + proteins_richness * relative_proteins_need +
                fats_richness * relative_fats_need;
        return ans;
    }

    private static int estimateNumberOfProducts(int n_days) {
        int month_items = 30;
        int day_items = 5;
        int ans = day_items + n_days * (month_items - day_items) / 30;
        if (ans > month_items) ans = month_items;
        return ans;
    }

    private static double computeQuantity(double calories, double calories_need, int n_days) {
        int n_products = estimateNumberOfProducts(n_days);
        return calories_need / calories / n_products;
    }

    public static List<Choice> giveSuggestions(UserPreferences preferences, Fridge fridge, int n_days) {
        double total_carbs_fridge = 0;
        double total_proteins_fridge = 0;
        double total_fats_fridge = 0;
        for (Item item : fridge.getItems()) {
            total_carbs_fridge += item.getCarbs();
            total_proteins_fridge += item.getProteins();
            total_fats_fridge += item.getFats();
        }

        double total_carbs_need = n_days * preferences.getCarbsPerDay() - total_carbs_fridge;
        double total_proteins_need = n_days * preferences.getProteinsPerDay() - total_proteins_fridge;
        double total_fats_need = n_days * preferences.getFatsPerDay() - total_fats_fridge;
        if (total_carbs_need < 0) total_carbs_need = 0;
        if (total_proteins_need < 0) total_proteins_need = 0;
        if (total_fats_need < 0) total_fats_need = 0;

        double total_calorie_need = NutritionalValue.computeCalories(total_carbs_need, total_proteins_need, total_fats_need);

        double relative_carbs_need = total_carbs_need / n_days / preferences.getCarbsPerDay();
        double relative_proteins_need = total_proteins_need / n_days / preferences.getProteinsPerDay();
        double relative_fats_need = total_fats_need / n_days / preferences.getFatsPerDay();

        List<Choice> ans = new ArrayList<Choice>();
        for (ItemType type : preferences.getTypes()) {
            double quantity = computeQuantity(type.nutritionalValue.getCalories(), total_calorie_need, n_days);
            double f1 = computeDecayFactor(type, n_days);
            double f2 = computeDesireFactor(type, preferences);
            double f3 = computeNatureFactor(type, preferences, relative_carbs_need, relative_proteins_need, relative_fats_need);
            double f4 = computeAvailabilityFactor(type);
            double f = (f1 * A_DECAY + B_DECAY) / (A_DECAY + B_DECAY);
            f *= (f2 * A_DESIRE + B_DESIRE) / (A_DESIRE + B_DESIRE);
            f *= (f3 * A_NATURE + B_NATURE) / (A_NATURE + B_NATURE);
            f *= (f4 * A_AVAILABILITY + B_AVAILABILITY) / (A_AVAILABILITY + B_AVAILABILITY);
            ans.add(new Choice(new Item(type, quantity, null), f, f4));
        }
        Collections.sort(ans);

        return ans;
    }

    public static class Choice implements Comparable<Choice> {
        public final Item item;
        public final double importance, availability;
        public Choice(Item item, double importance, double availability) {
            this.item = item;
            this.importance = importance;
            this.availability = availability;
        }

        @Override
        public int compareTo(@NonNull Choice choice) {
            if (importance > choice.importance) return -1;
            else if (importance == choice.importance) return 0;
            else return 1;
        }
    }
}
