package com.example.gabri.intellifridge.engine;

import org.json.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Talksick on 3/24/2018.
 */

public class Recipe {
    private String name;
    private String recipeURL, imageURL;

    public Recipe(String name, String recipeURL, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
        this.recipeURL = recipeURL;
    }

    public String toString() {
        return name;
    }

    public static List<Recipe> parseRecipes(String s) {
        List<Recipe> ans = new ArrayList<Recipe>();
        try {
            JSONArray hits = new JSONObject(s).getJSONArray("hits");
            for (int i = 0; i < hits.length(); ++i) {
                JSONObject tmp = hits.getJSONObject(i).getJSONObject("recipe");
                ans.add(new Recipe(tmp.getString("label"), tmp.getString("url"), tmp.getString("image")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ans;
    }
}
