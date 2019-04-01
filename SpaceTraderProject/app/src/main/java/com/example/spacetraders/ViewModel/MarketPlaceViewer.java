package com.example.spacetraders.ViewModel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.Entity.TechLevel;
import com.example.spacetraders.Entity.TradeGood;
import com.example.spacetraders.Model.MarketPlace;
import com.example.spacetraders.R;
import com.example.spacetraders.ViewModel.PlanetFragment.CargoFragment;
import com.example.spacetraders.ViewModel.PlanetFragment.MarketFragment;
import com.example.spacetraders.ViewModel.PlanetFragment.PlanetInfoFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketPlaceViewer extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private DatabaseReference mMarketDatabase;
    private DatabaseReference mPlayerDatabase;

    private String universeName;
    private String universeTechLevel;
    private int universeTechLevelInt;

    private FirebaseUser mCurrentUser;
    private String current_uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place_viewer);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universeName = extras.getString("Universe name");
            universeTechLevel = extras.getString("Universe_techlevel");
        } else {
            universeName = "Lave";
            universeTechLevel = "well shit this sucks";
        }

        mMarketDatabase = FirebaseDatabase.getInstance().getReference().child("markets");

        mMarketDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(universeName)) {
                    HashMap<String, Integer> techLevelMap = new HashMap<>();
                    for (TechLevel x: TechLevel.values()) {
                        techLevelMap.put(x.name(), x.getCode());
                    }
                    universeTechLevelInt = techLevelMap.get(universeTechLevel);
                    MarketPlace planetMarket = new MarketPlace(universeTechLevelInt);

                    TradeGood[] planetMarketItems = planetMarket.getItemsForSale();

                    Map<String, Object> childUpdates = new HashMap<>();

                    for (TradeGood item: planetMarketItems) {
                        if (item != null) {
                            Map<String, Object> itemValues = item.toMap();
                            childUpdates.put(item.getName(), itemValues);
                        }
                    }
                    mMarketDatabase.child(universeName).updateChildren(childUpdates);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (FirebaseAuth.getInstance() ==  null) {
            Toast.makeText(getApplicationContext(), "NULL USER", Toast.LENGTH_LONG).show();
        }
        current_uID = mCurrentUser.getUid();
        mPlayerDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(current_uID);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MarketFragment(), "Market Tab");
        adapter.addFragment(new CargoFragment(), "Cargo Tab");
        adapter.addFragment(new PlanetInfoFragment(), "Planet Tab");
        viewPager.setAdapter(adapter);
    }


    private class SectionsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
