package com.example.spacetraders.Views;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Model.Player;
import com.example.spacetraders.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseUser mCurrentUser;
    private String current_uID;
    private DatabaseReference mProfileDatabase;

    private TextView profileUserName;
    private TextView profileEmail;
    private TextView profileSkillPoints;
    private TextView profilePilotPoints;
    private TextView profileFigherPoints;
    private TextView profileTraderPoints;
    private TextView profileEngineerPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileUserName = findViewById(R.id.profileUserName);
        profileEmail = findViewById(R.id.profileEmail);
        profileSkillPoints = findViewById(R.id.profileskillPoints);
        profilePilotPoints = findViewById(R.id.profilePilotPoints);
        profileFigherPoints = findViewById(R.id.profileFighterPoints);
        profileTraderPoints = findViewById(R.id.profileTraderPoints);
        profileEngineerPoints = findViewById(R.id.profileEngineerPoints);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (FirebaseAuth.getInstance() ==  null) {
//            Toast.makeText(getApplicationContext(), "NULL", Toast.LENGTH_LONG).show();
//        }
        current_uID = mCurrentUser.getUid();
        mProfileDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(current_uID);

        ValueEventListener profileListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Player player = dataSnapshot.getValue(Player.class);
                profileUserName.setText(profileUserName.getText().toString() + " " + player.getUserName());
                profileEmail.setText(profileEmail.getText().toString() + " " + player.getEmail());
                profileSkillPoints.setText(profileSkillPoints.getText().toString() + " " + player.getSkillPoints());
                profilePilotPoints.setText(profilePilotPoints.getText().toString() + " " + dataSnapshot.child("pilotPoints").getValue().toString());
                profileFigherPoints.setText(profileFigherPoints.getText().toString() + " " + dataSnapshot.child("fighterPoints").getValue().toString());
                profileTraderPoints.setText(profileTraderPoints.getText().toString() + " " + dataSnapshot.child("traderPoints").getValue().toString());
                profileEngineerPoints.setText(profileEngineerPoints.getText().toString() + " " + dataSnapshot.child("engineerPoints").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mProfileDatabase.addListenerForSingleValueEvent(profileListener);


    }

    @Override
    protected void onStart() {
        super.onStart();



    }
}