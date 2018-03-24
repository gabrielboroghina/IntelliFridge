package com.example.gabri.intellifridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Button btn = findViewById(R.id.btn_login3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(login);
            }
        });*/

        if (!registered) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }
    }
}
