package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spacetraders.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Since we don't need a method checking if the user is logged in already, this just sends
        // the user straight to the login page
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
