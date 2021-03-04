package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    final ArrayList<ItemData> arrayOfUsers = new ArrayList<>();
    CustomAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        arrayOfUsers.add(new ItemData("Pizza ","Margarita"));
        arrayOfUsers.add(new ItemData("Pizza ","Thon"));
        arrayOfUsers.add(new ItemData("Pizza ","Végétarienne"));
        arrayOfUsers.add(new ItemData("Pizza ","Portofino"));
        arrayOfUsers.add(new ItemData("Pizza ","Dind"));
        arrayOfUsers.add(new ItemData("Pizza ","Fruit de Mer"));
        arrayOfUsers.add(new ItemData("Pizza ","Riad Essalam"));
        arrayOfUsers.add(new ItemData("Pizza ","Quatre Saisons"));
        arrayOfUsers.add(new ItemData("Pizza ","Mexicaine"));
        arrayOfUsers.add(new ItemData("Pizza ","4 Fromage"));
        arrayOfUsers.add(new ItemData("Pizza ","null"));
        arrayOfUsers.add(new ItemData("Pizza ","null"));
        arrayOfUsers.add(new ItemData("Pizza ","null"));
        arrayOfUsers.add(new ItemData("Pizza ","null"));
        arrayOfUsers.add(new ItemData("Pizza ","null"));
        adapter = new CustomAdapter(this, arrayOfUsers);

        listView = (ListView) findViewById(R.id.items);
        listView.setAdapter(adapter);
    }
}