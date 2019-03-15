package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.ViewModel.MarketPlaceViewer;
import com.example.spacetraders.ViewModel.UniverseSelectionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button buttonProfile;
    Button signOutButton;
    Button exitAppButton;
    Button buttonUniverse;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProfile = findViewById(R.id.buttonProfile);
        buttonUniverse = findViewById(R.id.buttonUniverses);
        signOutButton = findViewById(R.id.buttonSignOut);
        exitAppButton = findViewById(R.id.buttonExitApp);

        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        Toast.makeText(getApplicationContext(), currentUser.getUid(), Toast.LENGTH_LONG).show();

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Signed Out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        exitAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarketPlaceViewer.class);
                startActivity(intent);
            }
        });

        buttonUniverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UniverseSelectionActivity.class);
                startActivity(intent);
            }
        });

    }
    public void changeSP(View view) {
    }
}
