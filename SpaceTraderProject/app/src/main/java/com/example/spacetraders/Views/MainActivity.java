package com.example.spacetraders.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.ViewModel.UniverseSelectionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private Button buttonProfile;
    private Button signOutButton;
    private Button exitAppButton;
    private Button buttonUniverse;
    private FirebaseAuth mAuth;

    private FirebaseUser mCurrentUser;
    private String current_uID;
    private DatabaseReference mPlayerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProfile = findViewById(R.id.buttonProfile);
        buttonUniverse = findViewById(R.id.buttonUniverses);
        signOutButton = findViewById(R.id.buttonSignOut);
        exitAppButton = findViewById(R.id.buttonExitApp);

        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL USER", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();
        mPlayerDatabase = FirebaseDatabase.getInstance().getReference().child("users");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), currentUser.getUid(), Toast.LENGTH_LONG).show();
        }
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
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
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

        mPlayerDatabase.child(current_uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("shipFuel")) {
                    mPlayerDatabase.child(current_uID).child("shipFuel").setValue(500);
                }
                if (!dataSnapshot.hasChild("cargoCapacity")) {
                    mPlayerDatabase.child(current_uID).child("cargoCapacity").setValue(0);
                }
                if (!dataSnapshot.hasChild("money")) {
                    mPlayerDatabase.child(current_uID).child("money").setValue(10000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }

    public void changeSP(View view) {
    }}
