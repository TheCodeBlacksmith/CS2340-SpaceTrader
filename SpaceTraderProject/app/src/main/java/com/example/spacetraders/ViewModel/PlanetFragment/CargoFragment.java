package com.example.spacetraders.ViewModel.PlanetFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Entity.TradeGood;
import com.example.spacetraders.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;


/**
 * A login screen that offers login via email/password.
 */
public class CargoFragment extends Fragment {


    private DatabaseReference mCargoDatabase;

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cargo_fragment, container, false);

        recyclerView = view.findViewById(R.id.cargo_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getContext(), "NULL USER", Toast.LENGTH_LONG).show();
        }
        String current_uID = Objects.requireNonNull(mCurrentUser).getUid();

        mCargoDatabase = FirebaseDatabase.getInstance()
                .getReference().child("cargo").child(current_uID);
        mCargoDatabase.keepSynced(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showCargo();
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

    private void showCargo() {
        Query query = mCargoDatabase;
        FirebaseRecyclerOptions<TradeGood> options = new FirebaseRecyclerOptions
                .Builder<TradeGood>()
                .setQuery(query, TradeGood.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<TradeGood, CargoFragment.CargoGoodHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CargoFragment.CargoGoodHolder holder,
                                            int position, @NonNull final TradeGood model) {
                holder.setDetails(model);

            }

            @NonNull
            @Override
            public CargoFragment.CargoGoodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                    int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.cargo_item, viewGroup, false);
                return new CargoGoodHolder(view);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    private class CargoGoodHolder extends RecyclerView.ViewHolder {

        final TextView item_name;
        final TextView item_price;
        final TextView item_quantity;

        CargoGoodHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.store_item_title);
            item_price = itemView.findViewById(R.id.store_item_price);
            item_quantity = itemView.findViewById(R.id.store_item_amount);

        }

        @SuppressWarnings("FeatureEnvy")
        @SuppressLint("SetTextI18n")
        void setDetails(TradeGood model) {
            //item_name.setText(model.getName());
            item_name.setText(model.getName());
            item_price.setText(String.valueOf(model.getFinalPrice()));
            item_quantity.setText(String.valueOf(model.getQuantity()));
        }
    }
}
