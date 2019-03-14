package com.example.spacetraders.ViewModel;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spacetraders.Entity.Marketplace;
import com.example.spacetraders.Entity.TradeGood;
import com.example.spacetraders.Model.Player;
import com.example.spacetraders.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MarketActivity extends AppCompatActivity {


    private FirebaseUser mCurrentUser;
    private String current_uID;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mMarketDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference();

        final String[] currPlanet = {""};

        mUserDatabase.child("users").child(current_uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.getValue(Player.class);
                currPlanet[0] = player.getCurrentPlanet();
                Toast.makeText(getApplicationContext(), "Current Planet: " + currPlanet[0], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mMarketDatabase = FirebaseDatabase.getInstance().getReference().child("markets");

        Marketplace market = new Marketplace();
        TradeGood[] items = market.getItemsForSale();
        for (TradeGood item: items) {
            Log.i("Item: ", item.toString());
            Map<String, Object> tradeItem = item.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(currPlanet + "_Market", tradeItem);
            mMarketDatabase.updateChildren(childUpdates);
        }
    }
}
