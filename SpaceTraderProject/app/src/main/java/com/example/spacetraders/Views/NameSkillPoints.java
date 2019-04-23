package com.example.spacetraders.Views;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Model.Player;
import com.example.spacetraders.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class NameSkillPoints extends AppCompatActivity {

    private String current_uID;
    private DatabaseReference mDatabase;


    private EditText name;
    private Button submitButton;

    private TextView pilotPoints;
    private TextView fighterPoints;
    private TextView traderPoints;
    private TextView engineerPoints;

    private Player p1;

    private final int spCounter = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        name = findViewById(R.id.nameEditText);
        submitButton = findViewById(R.id.submitNameButton);

        //noinspection unused
        Button plusButtonPilot = findViewById(R.id.buttonPlusPilot);
        //noinspection unused
        Button plusButtonFighter = findViewById(R.id.buttonPlusFighter);
        //noinspection unused
        Button plusButtonTrader = findViewById(R.id.buttonPlusTrader);
        //noinspection unused
        Button plusButtonEngineer = findViewById(R.id.buttonPlusEngineer);
        //noinspection unused
        Button minusButtonPilot = findViewById(R.id.buttonMinusPilot);
        //noinspection unused
        Button minusButtonFighter = findViewById(R.id.buttonMinusFighter);
        //noinspection unused
        Button minusButtonTrader = findViewById(R.id.buttonMinusTrader);
        //noinspection unused
        Button minusButtonEngineer = findViewById(R.id.buttonMinusEngineer);


        pilotPoints = findViewById(R.id.textSPPilot);
        fighterPoints = findViewById(R.id.textSPFighter);
        traderPoints = findViewById(R.id.textSPTrader);
        engineerPoints = findViewById(R.id.textSPEngineer);

        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL", Toast.LENGTH_LONG).show();
        }
        current_uID = Objects.requireNonNull(mCurrentUser).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        p1 = new Player(mCurrentUser.getEmail(), name.getText().toString());

        updateSPFields();


    }

    @Override
    protected void onStart() {
        super.onStart();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(name.getText().toString())) {
                    if (p1.getSkillPoints() != spCounter) {
                        AlertDialog alertDialog = new AlertDialog.Builder(NameSkillPoints.this)
                                .create();
                        alertDialog.setTitle("Skill Point Allocation");
                        alertDialog.setMessage("You need to allocate "
                                + (spCounter - p1.getSkillPoints()) + " more points");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        p1.setUserName(name.getText().toString());
                        writeUserData(p1);
                    }
                }
            }
        });
    }


    private void writeUserData(Player player) {
        mDatabase.child("users").child(current_uID).setValue(player)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data Upated!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NameSkillPoints.this, Registration.class);
                        Toast.makeText(getApplicationContext(), "moved on",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Could not update data",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * A login screen that offers login via email/password.
     * @pram view a view
     */
    public void changeSP(View view) {
        if (view.getId() == R.id.buttonPlusPilot) {
            p1.changePoints(0, 1);
        } else if (view.getId() == R.id.buttonMinusPilot) {
            p1.changePoints(0, -1);
        } else if (view.getId() == R.id.buttonPlusFighter) {
            p1.changePoints(1, 1);
        } else if (view.getId() == R.id.buttonMinusFighter) {
            p1.changePoints(1, -1);
        } else if (view.getId() == R.id.buttonPlusTrader) {
            p1.changePoints(2, 1);
        } else if (view.getId() == R.id.buttonMinusTrader) {
            p1.changePoints(2, -1);
        } else if (view.getId() == R.id.buttonPlusEngineer) {
            p1.changePoints(3, 1);
        } else if (view.getId() == R.id.buttonMinusEngineer) {
            p1.changePoints(3, -1);
        }

        updateSPFields();
    }

    @SuppressLint("SetTextI18n")
    private void updateSPFields() {
        pilotPoints.setText("Skill Points: " + String.valueOf(p1.getPilotPoints()));
        fighterPoints.setText("Skill Points: " + String.valueOf(p1.getFighterPoints()));
        traderPoints.setText("Skill Points: " + String.valueOf(p1.getTraderPoints()));
        engineerPoints.setText("Skill Points: " + String.valueOf(p1.getEngineerPoints()));
    }

    // send to main activity when done
}
