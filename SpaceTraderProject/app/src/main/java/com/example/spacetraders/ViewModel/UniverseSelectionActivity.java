package com.example.spacetraders.ViewModel;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetraders.Entity.Planet;
import com.example.spacetraders.Entity.Resource;
import com.example.spacetraders.Entity.TechLevel;
import com.example.spacetraders.Model.Universe;
import com.example.spacetraders.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UniverseSelectionActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter mAdapter;

    private DatabaseReference mUniverseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_selection);

        recyclerView = findViewById(R.id.universe_list);


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mUniverseDatabase = FirebaseDatabase.getInstance().getReference().child("universe");
        for (int i = 0; i < 10; i++) {
            generateUniverses();
        }

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

    }

    private void updateUniverses() {

        Query query = mUniverseDatabase;
        FirebaseRecyclerOptions<Universe> options = new FirebaseRecyclerOptions.Builder<Universe>()
                .setQuery(query, Universe.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Universe, UniverseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UniverseViewHolder holder, int position, @NonNull Universe model) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();
                // TODO: ADD the onclick listener for each universe
                holder.setDetails(model);
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

        TextView universe_title;
        TextView universe_desc;
        TextView universe_loc;

        public UniverseViewHolder(View inflate) {
            super(inflate);
            universe_title = itemView.findViewById(R.id.universe_item_title);
            universe_desc = itemView.findViewById(R.id.universe_item_desc);
            universe_loc = itemView.findViewById(R.id.universe_item_location);
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
