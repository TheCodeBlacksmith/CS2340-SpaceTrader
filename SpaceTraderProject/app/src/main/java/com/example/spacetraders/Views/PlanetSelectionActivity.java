package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spacetraders.R;
import com.example.spacetraders.ViewModel.UniverseSelectionActivity;

public class PlanetSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_selection);


        Button planetButton = findViewById(R.id.planetSelectionButton);

        planetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UniverseSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
