package com.example.spacetraders.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spacetraders.Entity.Player;
import com.example.spacetraders.R;

import java.util.Objects;
import java.util.jar.Attributes;

public class NameSkllPoints extends AppCompatActivity {


    //String password = Objects.requireNonNull(getIntent().getExtras()).getString("password");

    EditText name;
    Button submitButton;
    Button plusButtonPilot;
    Button plusButtonFighter;
    Button plusButtonTrader;
    Button plusButtonEngineer;
    Button minusButtonPilot;
    Button minusButtonFighter;
    Button minusButtonTrader;
    Button minusButtonEngineer;

    TextView pilotPoints;
    TextView fighterPoints;
    TextView traderPoints;
    TextView engineerPoints;

    Player p1;

    int spCounter = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        name = (EditText) findViewById(R.id.nameEditText);
        submitButton = (Button) findViewById(R.id.submitNameButton);

        plusButtonPilot = findViewById(R.id.buttonPlusPilot);
        plusButtonFighter = findViewById(R.id.buttonPlusFighter);
        plusButtonTrader = findViewById(R.id.buttonPlusTrader);
        plusButtonEngineer = findViewById(R.id.buttonPlusEngineer);
        minusButtonPilot = findViewById(R.id.buttonMinusPilot);
        minusButtonFighter = findViewById(R.id.buttonMinusFighter);
        minusButtonTrader = findViewById(R.id.buttonMinusTrader);
        minusButtonEngineer = findViewById(R.id.buttonMinusEngineer);


        pilotPoints = findViewById(R.id.textSPPilot);
        fighterPoints = findViewById(R.id.textSPFighter);
        traderPoints = findViewById(R.id.textSPTrader);
        engineerPoints = findViewById(R.id.textSPEngineer);

        p1 = new Player(name.getText().toString(), "password");

        updateSPFields();



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    if (p1.getSkillPoints() != 16) {
                        AlertDialog alertDialog = new AlertDialog.Builder(NameSkllPoints.this).create();
                        alertDialog.setTitle("Skill Point Allocation");
                        alertDialog.setMessage("You need to allocate " + (16 - p1.getSkillPoints()) + " more points");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {

                        Intent intent = new Intent(NameSkllPoints.this, Registration.class);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("skillpoints", p1.getSkillPointsDistrib());
                        startActivity(intent);
                    }
                }
            }
        });



    }
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

    public void updateSPFields() {
        pilotPoints.setText("Skill Points: " + String.valueOf(p1.getPilotPoints()));
        fighterPoints.setText("Skill Points: " + String.valueOf(p1.getFighterPoints()));
        traderPoints.setText("Skill Points: " + String.valueOf(p1.getTraderPoints()));
        engineerPoints.setText("Skill Points: " + String.valueOf(p1.getEngineerPoints()));
    }

    // send to main activity when done
}
