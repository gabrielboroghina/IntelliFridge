package com.example.gabri.intellifridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gabri.intellifridge.util.DBRequest;

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

        // load food grades data
        req = new DBRequest("get_grades.php");
        try {
            req.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!registered) { // go to the registration page
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(login, 1);
        } else {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check which request we're responding to
        if (requestCode == 1) {
            startActivityForResult(new Intent(getApplicationContext(), MenuActivity.class), 3);
        } else if (requestCode == 3)
            this.finish();
    }
}
