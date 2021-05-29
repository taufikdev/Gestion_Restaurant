package com.aaexample.restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aaexample.restaurant.classes.Category;
import com.aaexample.restaurant.classes.Commande;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    private ArrayList<Commande> Commands;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        lstView = findViewById(R.id.cmdLstView);




        FirebaseDatabase db = FirebaseDatabase.getInstance();

        DatabaseReference ref = db.getReference("Commandes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Commands = new ArrayList<Commande>();
                for (DataSnapshot c:snapshot.getChildren()) {
                    Commande cmd = c.getValue(Commande.class);
                    Commands.add(new Commande(cmd.getId(),cmd.getEtat(),cmd.getUser(),cmd.getTable()));
                }
                lstView.setAdapter(new CmdAdapter(getApplicationContext(), Commands,0));
                Toast.makeText(getApplicationContext(), "remplissage",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}