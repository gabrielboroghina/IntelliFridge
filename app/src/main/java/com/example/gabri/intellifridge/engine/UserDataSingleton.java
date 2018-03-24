package com.example.gabri.intellifridge.engine;

/**
 * Created by gabri on 3/25/2018.
 */

public class UserDataSingleton {
    private static UserDataSingleton instance = null;
    private Fridge fridge;
    private UserPreferences uPref;

    private UserDataSingleton() {
        fridge = new Fridge();
        uPref = new UserPreferences();
    }

    public static UserDataSingleton getInstance() {
        if (instance == null)
            instance = new UserDataSingleton();

        return instance;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public UserPreferences getuPref() {
        return uPref;
    }
}
