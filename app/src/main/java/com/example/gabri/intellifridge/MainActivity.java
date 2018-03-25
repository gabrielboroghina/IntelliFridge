package com.example.gabri.intellifridge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabri.intellifridge.engine.ItemType;
import com.example.gabri.intellifridge.engine.UserDataSingleton;
import com.example.gabri.intellifridge.util.DBRequest;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load food data
        DBRequest req = new DBRequest("get_food.php");
        try {
            req.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req = new DBRequest("get_grades.php");
        try {
            req.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startActivity(new Intent(getApplicationContext(), MenuActivity.class));

        /*if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }*/
    }
}
