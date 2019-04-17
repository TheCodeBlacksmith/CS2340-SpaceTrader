package com.example.spacetraders.ViewModel.PlanetFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;

import java.util.Date;


@SuppressWarnings("ALL")
public class PlanetInfoFragment extends Fragment {


    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.planetinfo_fragment, container, false);

        button = view.findViewById(R.id.button);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dateTime = (int) (new Date().getTime()/1000);
                dateTime = dateTime % 100;
                Toast.makeText(getContext(), String.valueOf(dateTime), Toast.LENGTH_SHORT).show();
                if (dateTime < 150) {
                    AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Alert!");
                    alertDialog.setMessage("Space Pirates attacked your ship and stole 30% of your money!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });


    }
}
