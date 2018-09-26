package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
            GroupFragment groupFragment = new GroupFragment();
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

}
