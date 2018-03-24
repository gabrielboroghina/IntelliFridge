package com.example.gabri.intellifridge.engine;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.example.gabri.intellifridge.util.*;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Cook {

    private static final String EDAMAM_URL = "https://api.edamam.com/search";
    private static final String EDAMAM_APP_ID = "d34031d2";
    private static final String EDAMAM_APP_KEY = "d5c068de7a5406391fbdab34c3f2b369";

    private static final int NO_INGREDIENTS_USED = 3;
    private static final int NO_RECIPES_PROPOSED = 10;

    public static List<Recipe> chooseRecipes(Fridge fridge) {
        List<Item> items = new ArrayList<Item>();
        int idx = 0;
        for (Item item : fridge.getItems()) {
            if (idx >= NO_INGREDIENTS_USED) break;
            ++idx;
            items.add(item);

        }
        if (items.size() == 0) return null; //No ingredients
        return Recipe.parseRecipes(requestFromAPI(items, NO_RECIPES_PROPOSED));
    }

    private static String requestFromAPI(List<Item> items, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(EDAMAM_URL);
        sb.append("?q=");
        for (Item item : items) {
            sb.append(item.type.name);
            sb.append('+');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("&app_id=");
        sb.append(EDAMAM_APP_ID);
        sb.append("&app_key=");
        sb.append(EDAMAM_APP_KEY);
        sb.append("&from=0&to=");
        sb.append(n);
    //    sb.append("&calories=");
    //    sb.append(min_cal);
     //   sb.append('-');
     //   sb.append(max_cal);

        try {
            return new HttpGetRequest().execute(sb.toString()).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
