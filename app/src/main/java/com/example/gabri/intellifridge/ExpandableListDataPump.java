package com.example.gabri.intellifridge;

import com.example.gabri.intellifridge.engine.ItemType;
import com.example.gabri.intellifridge.engine.UserDataSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        List<String> veg = new ArrayList<String>();
        List<String> fruits = new ArrayList<String>();
        List<String> meat = new ArrayList<String>();
        List<String> fish = new ArrayList<String>();
        List<String> diary = new ArrayList<String>();
        List<String> grains = new ArrayList<String>();
        List<String> nuts = new ArrayList<String>();

        for (Map.Entry<String, ItemType> e : UserDataSingleton.getInstance().getuPref().known_types.entrySet())
            if (e.getValue().category.equals("Vegetables"))
                veg.add(e.getKey());
            else if (e.getValue().category.equals("Fruits"))
                fruits.add(e.getKey());
            else if (e.getValue().category.equals("Meat"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("Fish"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("Diary"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("Meat"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("Grains"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("Nuts"))
                meat.add(e.getKey());

        expandableListDetail.put("Vegetables", veg);
        expandableListDetail.put("Fruits", fruits);
        expandableListDetail.put("Meat", meat);
        expandableListDetail.put("Fish", fruits);
        expandableListDetail.put("Diary", diary);
        expandableListDetail.put("Grains", grains);
        expandableListDetail.put("Nuts", nuts);
        return expandableListDetail;
    }
}
