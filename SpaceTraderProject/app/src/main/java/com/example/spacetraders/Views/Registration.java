package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.spacetraders.R;

public class Registration extends AppCompatActivity {

    Button buttonBeginner;
    Button buttonEasy;
    Button buttonNormal;
    Button buttonHard;
    Button buttonImpossible;
    String difficultyLevel = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonBeginner = findViewById(R.id.buttonBeginner);
        buttonEasy = findViewById(R.id.buttonEasy);
        buttonNormal = findViewById(R.id.buttonNormal);
        buttonHard = findViewById(R.id.buttonHard);
        buttonImpossible = findViewById(R.id.buttonImpossible);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void onDifficultyButtonClicked(View view) {
        if (view.getId() == R.id.buttonBeginner) {
            difficultyLevel = "Beginner";
        } else if (view.getId() == R.id.buttonEasy) {
            difficultyLevel = "Easy";
        } else if (view.getId() == R.id.buttonNormal) {
            difficultyLevel = "Normal";
        } else if (view.getId() == R.id.buttonHard) {
            difficultyLevel = "Hard";
        } else if (view.getId() == R.id.buttonImpossible) {
            difficultyLevel = "Impossible";
        }

        Bundle extras = getIntent().getExtras();
        String name = "";
        int[] SPDistrib = new int[4];
        if (extras != null){
            name = extras.getString("name");
            SPDistrib = (int[]) extras.get("skillpoints");

        }

        Log.i("UserName: ", "" + name);
        Log.i("Difficulty Level: ", "" + difficultyLevel);
        Log.i("Pilot Points: ", String.valueOf(SPDistrib[0]));
        Log.i("Fighter Points: ", String.valueOf(SPDistrib[1]));
        Log.i("Trader Points: ", String.valueOf(SPDistrib[2]));
        Log.i("Engineer Points: ", String.valueOf(SPDistrib[3]));


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();


    }
}
