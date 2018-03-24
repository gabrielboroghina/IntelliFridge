package com.example.gabri.intellifridge.engine;

import java.util.List;

/**
 * Created by Talksick on 3/24/2018.
 */


public class Cook {

    public static final String EDAMAM_URL = "https://api.edamam.com/search";
    public static final String EDAMAM_APP_ID = "d34031d2";
    public static final String EDAMAM_APP_KEY = "d5c068de7a5406391fbdab34c3f2b369";

    List<Item> items;
    public Cook(List<Item> items) {
        this.items = items;
    }

    public List<Recipe> chooseRecipes() {
        return Recipe.parseRecipes(requestFromAPI(items, 3, 100, 1000));
    }

    private String requestFromAPI(List<Item> items, int n, int min_cal, int max_cal) {
        StringBuilder sb = new StringBuilder();
        sb.append(EDAMAM_URL);
        sb.append("?q=");
        for (Item item : items) {
            sb.append(item.getName());
            sb.append('+');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("&app_id=");
        sb.append(EDAMAM_APP_ID);
        sb.append("&app_key=");
        sb.append(EDAMAM_APP_KEY);
        sb.append("&from=0&to=" + n);
        sb.append("&calories=" + min_cal + '-' + max_cal);

        return sb.toString();
    }
}
