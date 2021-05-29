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
import android.widget.TextView;
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
import java.util.Date;

public class Item extends AppCompatActivity {

    final ArrayList<ListItem> arrayOfItems = new ArrayList<>();
    MyAdapter adapter;
    ListView listView;
    float montant=0;
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
                    arrayOfItems.add(new ListItem(itm.getName(),idimg,itm.getPrice()));
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
                EditText etnotes = v.findViewById(R.id.etnotes);
                Button btnvalidate = v.findViewById(R.id.validate);
                TextView tvprice = view.findViewById(R.id.plat_price);
                TextView tvname = view.findViewById(R.id.plat_name);
                btnvalidate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idcmd = getIntent().getStringExtra("idcmd");
                        String user = MyApplication.gUser;
                        String name = tvname.getText().toString();
                        String notes = etnotes.getText().toString();
                        float price = Float.valueOf(tvprice.getText().toString());


                        FirebaseDatabase db = FirebaseDatabase.getInstance();

                        DatabaseReference commandes = db.getReference("Commandes").child(idcmd);
                        String iditem = commandes.child("Items").push().getKey();
                        commandes.child("Items").child(iditem).setValue(new Iteme(name,price,notes));
//                        Toast.makeText(getApplicationContext(), montant,Toast.LENGTH_SHORT).show();
                        commandes.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                               Commande cmd = snapshot.getValue(Commande.class);
                                montant = cmd.getMontant();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        commandes.child("montant").setValue(montant+price);
                        // commandes.child("notes").setValue(notes);
                       // Query checkUser = db.getReference("Commandes").orderByChild().equalTo(idcmd);
                        //Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_SHORT).show();
                       // CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}