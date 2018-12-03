package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setDefaultFragmentForBottomNav(0);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        changeMainLayout(0);
                        return true;

                    case R.id.navigation_notifications:
                        changeMainLayout(1);
                        return true;

                    case R.id.navigation_group:
                        changeMainLayout(2);
                        return true;

                    case R.id.navigation_menu:
                        changeMainLayout(3);
                        return true;

                }
                return false;
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groupDatabaseReference = database.getReference("movies");

        groupDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    Movie userModel = userSnapshot.getValue(Movie.class);
                    Movie.allMovies.add(userModel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FirebaseMessaging.getInstance().subscribeToTopic("android");

    }

    public void setDefaultFragmentForBottomNav(int id) {
        navigation.setSelectedItemId(R.id.navigation_home);
        changeMainLayout(id);
    }

    public void changeMainLayout(int viewId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if ( viewId == 0 ) {
            MainFragment mainFragment = new MainFragment();
            ft.replace(R.id.fragment_container, mainFragment);
        }
        else if ( viewId == 1 ) {
            NotificationFragment notificationFragment = new NotificationFragment();
            ft.replace(R.id.fragment_container, notificationFragment);
        }
        else if ( viewId == 2 ) {
            Log.d(TAG, "changeMainLayout: " + EventFragment.groups.size());
            GroupFragment groupFragment = GroupFragment.newInstance(EventFragment.groups);
            ft.replace(R.id.fragment_container, groupFragment);
        }
        else if ( viewId == 3 ) {
            AccountFragment accountFragment = new AccountFragment();
            ft.replace(R.id.fragment_container, accountFragment);
        }
        else {
            return;
        }
        ft.commit();
    }

//    public void onChipClick(View view)
//    {
//        if (view instanceof Button)
//        {
//            Button btn = (Button) view;
//            if( btn.getTag().equals("0"))
//            {
//                btn.setTag("1");
//                btn.setBackgroundResource(R.drawable.my_chip_selected);
//            }
//            else
//            {
//                btn.setTag("0");
//                btn.setBackgroundResource(R.drawable.my_chip);
//            }
//        }
//
//    }



}
