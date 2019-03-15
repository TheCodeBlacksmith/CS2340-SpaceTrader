package com.example.spacetraders.ViewModel.PlanetFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Entity.TradeGood;
import com.example.spacetraders.Model.Player;
import com.example.spacetraders.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class MarketFragment extends Fragment {

    private FirebaseUser mCurrentUser;
    private String current_uID;
    private DatabaseReference mPlayerDatabase;
    private DatabaseReference mMarketDatabase;
    private DatabaseReference mCargoDatabase;
    String currentMarket = "";


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_market, container, false);

        recyclerView = view.findViewById(R.id.market_list);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //Log.i("Current Market Location3", currentMarket);
        showMarket();

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getContext(), "NULL USER", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();


        mMarketDatabase = FirebaseDatabase.getInstance().getReference().child("markets");
        mCargoDatabase = FirebaseDatabase.getInstance().getReference().child("cargo").child(current_uID);
        //Log.i("Current Market Location2", currentMarket);
    }

    private void showMarket() {

        mPlayerDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mPlayerDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentMarket = (String) dataSnapshot.child("currentPlanet").getValue();
                Log.i("Current Market Location", currentMarket);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Query query = mMarketDatabase.child("Lave");
        FirebaseRecyclerOptions<TradeGood> options = new FirebaseRecyclerOptions.Builder<TradeGood>()
                .setQuery(query, TradeGood.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<TradeGood, TradeGoodHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TradeGoodHolder holder, int position, @NonNull final TradeGood model) {
                holder.setDetails(model);
                holder.buyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int itemQuantity = Integer.parseInt(holder.item_quantity.getText().toString());
                        itemQuantity--;
                        holder.item_quantity.setText(String.valueOf(itemQuantity));
                        purchaseItem(model);
                    }
                });
                holder.sellButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), "Sell Button Clicked", Toast.LENGTH_SHORT).show();
                        int itemQuantity = Integer.parseInt(holder.item_quantity.getText().toString());
                        itemQuantity++;
                        holder.item_quantity.setText(String.valueOf(itemQuantity));
                        sellItem(model);
                    }
                });
            }


            @NonNull
            @Override
            public TradeGoodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.market_item, viewGroup, false);
                return new TradeGoodHolder(view);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    private class TradeGoodHolder extends RecyclerView.ViewHolder {

        TextView item_name;
        TextView item_price;
        TextView item_quantity;
        Button buyButton;
        Button sellButton;

        public TradeGoodHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.market_item_title);
            item_price = itemView.findViewById(R.id.market_item_price);
            item_quantity = itemView.findViewById(R.id.market_item_amount);
            buyButton = itemView.findViewById(R.id.market_buy_button);
            sellButton = itemView.findViewById(R.id.market_sell_button);
        }

        @SuppressLint("SetTextI18n")
        public void setDetails(TradeGood model) {
            //item_name.setText(model.getName());
            item_name.setText(model.getName());
            item_price.setText(String.valueOf(model.getFinalPrice()));
            item_quantity.setText(String.valueOf(model.getQuantity()));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private void purchaseItem(final TradeGood model) {
        mCargoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child(model.getName());
                if (dataSnapshot.exists()) {
                    long newValue = (long) dataSnapshot.child("quantity").getValue();
                    newValue++;
                    mCargoDatabase.child(model.getName()).child("quantity").setValue(newValue);
                    Toast.makeText(getContext(), "Item sucessfully bought!", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> childUpdates = new HashMap<>();
                    model.setQuantity(1);
                    childUpdates.put(model.getName(), model);
                    mCargoDatabase.updateChildren(childUpdates);
                    Toast.makeText(getContext(), "Item sucessfully bought!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void sellItem(final TradeGood model) {
        mCargoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child(model.getName());
                if (dataSnapshot.exists()) {
                    long newValue = (long) dataSnapshot.child("quantity").getValue();
                    newValue--;
                    mCargoDatabase.child(model.getName()).child("quantity").setValue(newValue);
                    if (newValue == 0) {
                        mCargoDatabase.child(model.getName()).removeValue();
                    }
                    Toast.makeText(getContext(), "Item sucessfully sold!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "You have not bought this item", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}