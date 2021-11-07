package com.example.doghub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNav);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Fragment1()).commit();


    }

    //declaring onNav
    private BottomNavigationView.OnNavigationItemSelectedListener onNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override

        //method that determines which fragment to go to based on user selection
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected = null;
            switch (item.getItemId()) {

                //sends to feed fragment
                case R.id.bottom_feed:
                    selected = new Fragment4();
                    break;

                //sends to search fragment
                case R.id.bottom_search:
                    selected = new Fragment2();
                    break;

                //sends to message fragment
                case R.id.bottom_message:
                    selected = new Fragment3();
                    break;

                //sends to profile fragment
                case R.id.bottom_profile:
                    selected = new Fragment1();
                    break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selected).commit();

            return true;
        }
    };


    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
