package com.example.gabri.intellifridge.engine;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public static void serialize() {
        try {
            String path = Environment.getDownloadCacheDirectory().getPath();
            File ser = new File("app_data");
            ser.createNewFile();

            FileOutputStream fos = new FileOutputStream("app_data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
            oos.close();
            fos.close();
            System.out.printf("Serialized!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("app_data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (UserDataSingleton) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
