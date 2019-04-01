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
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class UniverseSelectionActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter mAdapter;

    private DatabaseReference mUniverseDatabase;

    private FirebaseUser mCurrentUser;
    private String current_uID;
    private DatabaseReference mPlayerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_selection);

        recyclerView = findViewById(R.id.universe_list);


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mUniverseDatabase = FirebaseDatabase.getInstance().getReference().child("universe");

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL USER", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();
        mPlayerDatabase = FirebaseDatabase.getInstance().getReference().child("users");


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("universe")) {
                    Toast.makeText(getApplicationContext(), String.valueOf(dataSnapshot.child("universe").getChildrenCount()) + " Universes exist", Toast.LENGTH_SHORT).show();
                    Universe firstUniverse = null;
                    for (DataSnapshot child: dataSnapshot.child("universe").getChildren()) {
                        firstUniverse = child.getValue(Universe.class);
                        break;
                    }
                    //Log.i("UniverseShit", firstUniverse.toString());
                    final Universe finalFirstUniverse = firstUniverse;
                    mPlayerDatabase.child(current_uID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.hasChild("currentPlanet")) {
                                mUniverseDatabase.orderByKey().limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    mPlayerDatabase.child(current_uID).child("currentPlanet").setValue(finalFirstUniverse.getPlanet());
                                    //mPlayerDatabase.child(current_uID).child("currentPlanetTechLevel").setValue(finalFirstUniverse.getTechLevel());
                                    mPlayerDatabase.child(current_uID).child("currentXCoord").setValue(finalFirstUniverse.getxCoordinate());
                                    mPlayerDatabase.child(current_uID).child("currentYCoord").setValue(finalFirstUniverse.getyCoordinate());
                                    Toast.makeText(getApplicationContext(), "Currently On: " + finalFirstUniverse.getPlanet(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Currently On: " + dataSnapshot.child("currentPlanet").getValue().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    for (int i = 0; i < 10; i++) {
                        generateUniverses();
                    }
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

        mPlayerDatabase.child(current_uID).child("currentPlanet").setValue(planetNames[planetNum]);
        mPlayerDatabase.child(current_uID).child("currentPlanetTechLevel").setValue(techLevelNum);
    }

    private void updateUniverses() {

        Query query = mUniverseDatabase;
        FirebaseRecyclerOptions<Universe> options = new FirebaseRecyclerOptions.Builder<Universe>()
                .setQuery(query, Universe.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Universe, UniverseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UniverseViewHolder holder, final int position, @NonNull final Universe model) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();
                //Log.i("this works tho", model.getPlanet());
                holder.setDetails(model);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Traveling to " + model.getPlanet(), Toast.LENGTH_SHORT).show();
                    }

                });
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

        public UniverseViewHolder(View itemView) {
            super(itemView);
            universe_title = itemView.findViewById(R.id.universe_item_title);
            universe_desc = itemView.findViewById(R.id.universe_item_desc);
            universe_loc = itemView.findViewById(R.id.universe_item_location);


            universe_desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MarketPlaceViewer.class);
                    intent.putExtra("Universe name", universe_title.getText().toString());
                    String techlevel = universe_desc.getText().toString();
                    techlevel = techlevel.substring(techlevel.indexOf("with") + 5);
                    //techlevel = techlevel.substring(0, techlevel.indexOf(" resources"));
                    intent.putExtra("Universe_techlevel",  techlevel.toUpperCase());
                    //mPlayerDatabase.child(current_uID).child("currentPlanet").setValue(universe_title.getText().toString());
                    String xCoord = universe_loc.getText().toString();
                    xCoord = xCoord.substring(xCoord.indexOf("<") + 1);
                    xCoord = xCoord.substring(0, xCoord.indexOf(","));
                    String yCoord = universe_loc.getText().toString();
                    yCoord = yCoord.substring(yCoord.indexOf(",") + 2);
                    yCoord = yCoord.substring(0, yCoord.indexOf(">"));
                    //mPlayerDatabase.child(current_uID).child("currentXCoord").setValue(Integer.parseInt(xCoord));
                    //mPlayerDatabase.child(current_uID).child("currentYCoord").setValue(Integer.parseInt(yCoord));
                    //Toast.makeText(getApplicationContext(), "Traveling to " + universe_title.getText().toString(), Toast.LENGTH_SHORT).show();
                    //startActivity(intent);

                    travel(intent, Integer.parseInt(xCoord), Integer.parseInt(yCoord), universe_title.getText().toString());
                }
            });

        }
        @SuppressLint("SetTextI18n")
        public void setDetails(Universe model) {
            universe_title.setText(model.getPlanet());
            // description: A <planet> with <resource> resources and <techlevel> techlevel
            // located at <x, y>
            String descAppend = "A " + model.getResource().toLowerCase() + " planet with "
                    + model.getTechLevel().toLowerCase();
            universe_desc.setText(descAppend);
            universe_loc.setText("Location: <" + model.getxCoordinate() + ", " + model.getyCoordinate() + ">");
        }
    }

    private void travel(final Intent intent, final int xCoord, final int yCoord, final String planetName) {
        mPlayerDatabase.child(current_uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int currentX = ((Long) dataSnapshot.child("currentXCoord").getValue()).intValue();
                int currentY = ((Long) dataSnapshot.child("currentYCoord").getValue()).intValue();
                int currentFuel = ((Long) dataSnapshot.child("shipFuel").getValue()).intValue();

                double x_dif = Math.abs(currentX - xCoord);
                double y_dif = Math.abs(currentY - yCoord);
                double distance = Math.hypot(x_dif, y_dif);
                int result = (int) Math.round(distance);
//                Log.i("DIF X", String.valueOf(x_dif));
//                Log.i("DIF Y", String.valueOf(y_dif));
//                Log.i("DIF", String.valueOf(result));
//                Log.i("Current FUel", String.valueOf(currentFuel));
//                Log.i("Current X", String.valueOf(currentX));
                result = currentFuel - result;
//                Log.i("DIF", String.valueOf(result));

                if (result > 0) {
                    mPlayerDatabase.child(current_uID).child("currentPlanet").setValue(planetName);
                    mPlayerDatabase.child(current_uID).child("currentXCoord").setValue(xCoord);
                    mPlayerDatabase.child(current_uID).child("currentYCoord").setValue(yCoord);
                    mPlayerDatabase.child(current_uID).child("shipFuel").setValue(result);
                    Toast.makeText(getApplicationContext(), "Traveling to " + planetName, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough fuel to travel to " + planetName, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
