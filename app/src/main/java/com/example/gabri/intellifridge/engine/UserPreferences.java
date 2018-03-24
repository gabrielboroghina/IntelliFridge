package com.example.gabri.intellifridge.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Talksick on 3/24/2018.
 */

public class UserPreferences {
    private Map<String, ItemType> known_types;
    private Map<ItemType, Double> grades;

    public UserPreferences() {
        this.known_types = new HashMap<String, ItemType>();
        this.grades = new HashMap<ItemType, Double>();
    }

    public void changeGrade(String name) {
        //known_types.get()
    }

    public void addPreference(String type_name) {
        //list.add(getItemByName(type_name));
    }
}
