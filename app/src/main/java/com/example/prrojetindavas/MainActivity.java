package com.example.prrojetindavas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.prrojetindavas.Activity.Login;
import com.example.prrojetindavas.Fragments.HomeFragment;
import com.example.prrojetindavas.Fragments.PaymentsFragment;
import com.example.prrojetindavas.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    HomeFragment homeFragment = new HomeFragment();
    PaymentsFragment paymentsFragment = new PaymentsFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
              switch (item.getItemId()){
                  case R.id.menu_principal:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                      return true;
                  case R.id.menu_pagamento:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,paymentsFragment).commit();
                      return true;
                  case R.id.menu_exit:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                      return true;
              }
              return false;
            }
        });

    /* btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });*/
    }
    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser currentuser =FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser == null){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

}