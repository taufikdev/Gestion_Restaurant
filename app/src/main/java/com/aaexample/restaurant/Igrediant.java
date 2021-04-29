package com.aaexample.restaurant;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Igrediant extends AppCompatActivity {

    ListView ingrediants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igrediant);
        ingrediants = findViewById(R.id.ingrediantList);

    }

}