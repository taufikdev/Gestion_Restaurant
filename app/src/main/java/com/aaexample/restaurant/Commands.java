package com.aaexample.restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Commands extends AppCompatActivity {

    private ArrayList<Commande> Commands,filteredCatItems;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);

        EditText search = findViewById(R.id.etsearch);

        lstView = findViewById(R.id.cmdLstView);


        FirebaseDatabase db = FirebaseDatabase.getInstance();

        int cmdstype = getIntent().getIntExtra("cmdtype",0);

        switch (cmdstype){
            case 0:
                //inprogress
                Query checkUser = db.getReference("Commandes").orderByChild("etat").equalTo("inProgress");
                checkUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Commands = new ArrayList<Commande>();
                        for (DataSnapshot c:snapshot.getChildren()) {
                            Commande cmd = c.getValue(Commande.class);
                            Commands.add(new Commande(cmd.getId(),cmd.getEtat(),cmd.getUser(),cmd.getTable()));
                        }
                        lstView.setAdapter(new CmdAdapter(getApplicationContext(), Commands,0));
                       // Toast.makeText(getApplicationContext(), "ready", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                break;
            case 1:
                //ready
                Query checkUser1 = db.getReference("Commandes").orderByChild("etat").equalTo("Ready");
                checkUser1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Commands = new ArrayList<Commande>();
                        for (DataSnapshot c:snapshot.getChildren()) {
                            Commande cmd = c.getValue(Commande.class);
                            Commands.add(new Commande(cmd.getId(),cmd.getEtat(),cmd.getUser(),cmd.getTable()));
                        }
                        lstView.setAdapter(new CmdAdapter(getApplicationContext(), Commands,1));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case 2:
                //served
                Query checkUser2 = db.getReference("Commandes").orderByChild("etat").equalTo("Served");
                checkUser2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Commands = new ArrayList<Commande>();
                        for (DataSnapshot c:snapshot.getChildren()) {
                            Commande cmd = c.getValue(Commande.class);
                            Commands.add(new Commande(cmd.getId(),cmd.getEtat(),cmd.getUser(),cmd.getTable()));
                        }
                        lstView.setAdapter(new CmdAdapter(getApplicationContext(), Commands,2));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
        }


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredCatItems = new ArrayList<Commande>();
                for (int i = 0; i < Commands.size(); i++){
                    String itemLabel, searchText;
                    itemLabel = Commands.get(i).getTable().toLowerCase();
                    searchText = search.getText().toString().toLowerCase();
                    if(itemLabel.contains(searchText)){
                        filteredCatItems.add(Commands.get(i));
                    }
                }
                lstView.setAdapter(new CmdAdapter(getApplicationContext(), filteredCatItems,cmdstype));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}