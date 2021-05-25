package com.aaexample.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aaexample.restaurant.classes.Category;
import com.aaexample.restaurant.classes.Commande;
import com.aaexample.restaurant.classes.Iteme;
import com.aaexample.restaurant.classes.MyApplication;
import com.aaexample.restaurant.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Item extends AppCompatActivity {

    final ArrayList<ListItem> arrayOfItems = new ArrayList<>();
    MyAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        listView = (ListView) findViewById(R.id.items);
        String Label = getIntent().getStringExtra("label");
        String cat = getIntent().getStringExtra("cat");

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        Query checkUser = db.getReference("Items").orderByChild("category").equalTo(cat);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot c:snapshot.getChildren()
                ) {
                    Iteme itm = c.getValue(Iteme.class);
                   // Toast.makeText(getApplicationContext(),itm.getName() ,Toast.LENGTH_LONG).show();
                   Context context = getApplicationContext();
                    int idimg = context.getResources().getIdentifier(itm.getImg(), "drawable", context.getPackageName());
                    arrayOfItems.add(new ListItem(itm.getName(),idimg));
                }


                adapter = new MyAdapter(getApplicationContext(), arrayOfItems);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Item.this);
                View v = getLayoutInflater().inflate(R.layout.notes,null);
                alertBuilder.setView(v);
                AlertDialog  alertDialog = alertBuilder.create();
                alertDialog.show();
                EditText etnotes = v.findViewById(R.id.notes);
                Button btnvalidate = v.findViewById(R.id.validate);
                String notes = etnotes.getText().toString();
                btnvalidate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idcmd = getIntent().getStringExtra("idcmd");
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference commandes = db.getReference("Commandes").child(idcmd);
                        commandes.child(notes).setValue(notes);
                       // Query checkUser = db.getReference("Commandes").orderByChild().equalTo(idcmd);
                        //Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}