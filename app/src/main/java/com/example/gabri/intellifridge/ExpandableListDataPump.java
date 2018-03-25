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
            if (e.getValue().category.equals("vegetables"))
                veg.add(e.getKey());
            else if (e.getValue().category.equals("fruits"))
                fruits.add(e.getKey());
            else if (e.getValue().category.equals("meat"))
                meat.add(e.getKey());
            else if (e.getValue().category.equals("fish"))
                fish.add(e.getKey());
            else if (e.getValue().category.equals("diary"))
                diary.add(e.getKey());
            else if (e.getValue().category.equals("grains"))
                grains.add(e.getKey());
            else if (e.getValue().category.equals("nuts"))
                nuts.add(e.getKey());

        expandableListDetail.put("Vegetables", veg);
        expandableListDetail.put("Fruits", fruits);
        expandableListDetail.put("Meat", meat);
        expandableListDetail.put("Fish", fish);
        expandableListDetail.put("Diary", diary);
        expandableListDetail.put("Grains", grains);
        expandableListDetail.put("Nuts", nuts);
        return expandableListDetail;
    }
}
