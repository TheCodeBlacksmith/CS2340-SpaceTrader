package com.example.spacetraders.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.spacetraders.R;

public class NameSkllPoints extends AppCompatActivity {
    TextView showValue;
    int spCounter = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showValue = (TextView) findViewById(R.id.currentSP);
    }
    public void countSP(View view) {
        spCounter--;
    }
}
