package com.example.spacetraders.ViewModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Entity.Planet;
import com.example.spacetraders.Entity.Resource;
import com.example.spacetraders.Entity.TechLevel;
import com.example.spacetraders.Model.Universe;
import com.example.spacetraders.R;
import com.example.spacetraders.Views.MainActivity;
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
import java.util.Random;

public class UniverseSelectionActivity extends AppCompatActivity {


    private FirebaseUser mCurrentUser;
    private String current_uID;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter mAdapter;

    private DatabaseReference mUniverseDatabase;
    private DatabaseReference mUserDatabase;

    int totalUniverses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_selection);

        recyclerView = findViewById(R.id.universe_list);


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference();

        mUniverseDatabase = FirebaseDatabase.getInstance().getReference().child("universe");
        mUniverseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                totalUniverses = (int) dataSnapshot.getChildrenCount();
                Log.i("TOTAl UNIVERSES:", String.valueOf(totalUniverses));
                for (int i = totalUniverses; i < 10; i++) {
                    generateUniverses();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateUniverses();

    }

    private void generateUniverses() {
        Random rand = new Random();
        String[] planetNames = Planet.SolarNames;
        Resource[] resourceNames = Resource.values();
        TechLevel[] techLevelNames = TechLevel.values();
        int planetNum = rand.nextInt(Planet.SolarNames.length);
        int resourceNum = rand.nextInt(Resource.values().length);
        int techLevelNum = rand.nextInt(TechLevel.values().length);
        int xCoordinate = rand.nextInt(150);
        int yCoordinate = rand.nextInt(100);

        Universe universe = new Universe(planetNames[planetNum], resourceNames[resourceNum].name(),
                techLevelNames[techLevelNum].name(), xCoordinate, yCoordinate);

        Map<String, Object> universeValues = universe.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(planetNames[planetNum], universeValues);
        mUniverseDatabase.updateChildren(childUpdates);
        mUserDatabase.child("users").child(current_uID).child("currentPlanet").setValue(planetNames[planetNum]);
        //TODO: adding the currentPlanet only works if all the universes are being generated (look at the for loop in above))

    }

    private void updateUniverses() {

        Query query = mUniverseDatabase;
        FirebaseRecyclerOptions<Universe> options = new FirebaseRecyclerOptions.Builder<Universe>()
                .setQuery(query, Universe.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Universe, UniverseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UniverseViewHolder holder, final int position, @NonNull Universe model) {
                holder.setDetails(model);

                //TODO: FIGURE OUT HOW OT TRAVEL BY CLICKIGN ON ITEM

            }

            @NonNull
            @Override
            public UniverseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new UniverseViewHolder(inflater.inflate(R.layout.universe_item, viewGroup, false));
            }

        };
        recyclerView.setAdapter(mAdapter);
    }

    private class UniverseViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView universe_title;
        TextView universe_desc;
        TextView universe_loc;

        public UniverseViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            universe_title = itemView.findViewById(R.id.universe_item_title);
            universe_desc = itemView.findViewById(R.id.universe_item_desc);
            universe_loc = itemView.findViewById(R.id.universe_item_location);

            universe_desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("idk man", "onClick: " + getAdapterPosition());
                }
            });

        }

        @SuppressLint("SetTextI18n")
        public void setDetails(Universe model) {
            universe_title.setText(model.getPlanet());
            // description: A <planet> with <resource> resources and <techlevel> techlevel
            // located at <x, y>
            String descAppend = "A " + model.getResource().toLowerCase() + " planet with "
                    + model.getTechLevel().toLowerCase() + " resources";
            universe_desc.setText(descAppend);
            universe_loc.setText("Location: <" + model.getxCoordinate() + ", " + model.getyCoordinate() + ">");
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

}
