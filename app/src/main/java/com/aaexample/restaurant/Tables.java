package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tables extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<ListItem> ListItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        gridView = findViewById(R.id.tablesGridView);

        ListItems = new ArrayList<ListItem>();
        for (int i = 0; i < 11; i++)
            ListItems.add(new ListItem("Table N: " + i, R.drawable.table1));

        gridView.setAdapter(new tabelsAdapter(getApplicationContext(), ListItems));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem ListItem = ListItems.get(position);
                Intent i = new Intent(getApplicationContext(), Categories.class);
                i.putExtra("label",ListItem.getmTableNumber());
                //Toast.makeText(getApplicationContext(),ListItem.getmTableNumber(),Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}