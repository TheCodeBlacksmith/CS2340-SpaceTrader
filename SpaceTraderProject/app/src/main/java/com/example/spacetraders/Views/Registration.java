package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spacetraders.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Once everything is finished


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
