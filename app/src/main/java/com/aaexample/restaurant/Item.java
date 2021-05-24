package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    final ArrayList<ItemData> arrayOfItems = new ArrayList<>();
    CustomAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        arrayOfItems.add(new ItemData("Pizza ","Margarita"));
        arrayOfItems.add(new ItemData("Pizza ","Thon"));
        arrayOfItems.add(new ItemData("Pizza ","Végétarienne"));
        arrayOfItems.add(new ItemData("Pizza ","Portofino"));
        arrayOfItems.add(new ItemData("Pizza ","Dind"));
        arrayOfItems.add(new ItemData("Pizza ","Fruit de Mer"));
        arrayOfItems.add(new ItemData("Pizza ","Riad Essalam"));
        arrayOfItems.add(new ItemData("Pizza ","Quatre Saisons"));
        arrayOfItems.add(new ItemData("Pizza ","Mexicaine"));
        arrayOfItems.add(new ItemData("Pizza ","4 Fromage"));

        adapter = new CustomAdapter(this, arrayOfItems);

        listView = (ListView) findViewById(R.id.items);
        listView.setAdapter(adapter);
    }
}