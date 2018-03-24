package com.example.gabri.intellifridge.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Talksick on 3/24/2018.
 */

public class UserPreferences {
    public static final double AVG_CARBS = 275;
    public static final double AVG_PROTEINS = 50;
    public static final double AVG_FATS = 70;

    public final static int MAX_GRADE = 5;

    private double n_persons;
    private Map<String, ItemType> known_types;
    private Map<ItemType, Integer> grades;

    public UserPreferences() {
        n_persons = 1;
        this.known_types = new HashMap<String, ItemType>();
        this.grades = new HashMap<ItemType, Integer>();
    }

    public double getCarbsPerDay() {
        return n_persons * AVG_CARBS;
    }

    public Set<ItemType> getTypes() {
        return grades.keySet();
    }

    public double getProteinsPerDay() {
        return n_persons * AVG_PROTEINS;
    }

    public double getFatsPerDay() {
        return n_persons * AVG_FATS;
    }

    public void addType(String name, String category, double perishability, double carbs, double proteins, double fats) {
        ItemType type = new ItemType(name, category, new NutritionalValue(carbs, proteins, fats), perishability);
        known_types.put(name, type);
    }

    public void addGrade(String name, int grade) {
        ItemType type = known_types.get(name);
        if (type == null) return;
        grades.put(type, grade);
    }

    public void setGradeByType(ItemType type, int x) {
        Integer grade = grades.get(type);
        if (grade == null) return;
        grades.put(type, x);
    }

    public int getGradeByType(ItemType type) {
        Integer tmp = grades.get(type);
        if (tmp == null) return 0;
        return tmp;
    }

    public void addPreference(String type_name) {
        //list.add(getItemByName(type_name));
    }
}
