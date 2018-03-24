package com.example.gabri.intellifridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> veg = new ArrayList<String>();
        veg.add("India");
        veg.add("Pakistan");
        veg.add("Australia");
        veg.add("England");

        List<String> fruits = new ArrayList<String>();
        fruits.add("Brazil");
        fruits.add("Spain");
        fruits.add("Germany");


        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");


        expandableListDetail.put("Vegetables", veg);
        expandableListDetail.put("Fruits", fruits);
        expandableListDetail.put("Meat", fruits);
        expandableListDetail.put("Fish", basketball);
        expandableListDetail.put("Diary", basketball);
        expandableListDetail.put("Grains", basketball);
        expandableListDetail.put("Nuts", basketball);
        return expandableListDetail;
    }
}
