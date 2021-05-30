package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaexample.restaurant.classes.Commande;
import com.aaexample.restaurant.classes.MyApplication;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Tables extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<ListItem> ListItems,filteredCatItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        EditText search = findViewById(R.id.etsearch);

        gridView = findViewById(R.id.tablesGridView);

        ListItems = new ArrayList<ListItem>();
        for (int i = 0; i < 11; i++)
            ListItems.add(new ListItem("Table N: " + i, R.drawable.table1));

        gridView.setAdapter(new tabelsAdapter(getApplicationContext(), ListItems));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListItem ListItem = ListItems.get(position);
                TextView table = view.findViewById(R.id.tableNum);
               // Toast.makeText(getApplicationContext(),table.getText().toString() ,Toast.LENGTH_SHORT).show();

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference commandes = db.getReference("Commandes");
                String idc = commandes.push().getKey();

                Date d = new Date();
                CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());


                Commande us = new Commande(idc, "inProgress",MyApplication.gUser,table.getText().toString(), s.toString(),0.0f);
                commandes.child(idc).setValue(us);

                Intent i = new Intent(getApplicationContext(), Categories.class);
             //   i.putExtra("label",ListItem.getmTableNumber());
                i.putExtra("idcmd",idc);
                startActivity(i);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredCatItems = new ArrayList<ListItem>();
                for (int i = 0; i < ListItems.size(); i++){
                    String itemLabel, searchText;
                    itemLabel = ListItems.get(i).getmTableNumber().toLowerCase();
                    searchText = search.getText().toString().toLowerCase();
                    if(itemLabel.contains(searchText)){
                        filteredCatItems.add(ListItems.get(i));
                    }
                }
                gridView.setAdapter(new tabelsAdapter(getApplicationContext(), filteredCatItems));
               // Toast.makeText(getApplicationContext(), "im changing", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}