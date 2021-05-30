package com.aaexample.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaexample.restaurant.classes.Iteme;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class Facture extends AppCompatActivity {

    private ArrayList<String> Items;
    private ListView lstView;
    private TextView date,amount,table;
    ArrayList<ItemData> listItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facture);
        date = findViewById(R.id.tvDate);
        amount = findViewById(R.id.tvAmount);
        table = findViewById(R.id.tvTableNum);
        lstView = findViewById(R.id.lstvFacture);

        Items = new ArrayList();
       /* Items.add("Pizza pepperoni\n30DH");
        Items.add("Tacos : 30DH");
        Items.add("Coca Cola : 10DH");*/

        String idcomd = getIntent().getStringExtra("idcomd");
        FirebaseDatabase db = FirebaseDatabase.getInstance();



        DatabaseReference ref = db.getReference("Commandes").child(idcomd).child("Items");

    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot itm:snapshot.getChildren()){
                Iteme itme = itm.getValue(Iteme.class);
             //   Toast.makeText(getApplicationContext(),itme.getName(),Toast.LENGTH_LONG).show();
                Items.add(itme.getName()+"\n"+itme.getPrice()+" Dhs");
               // listItems.add(new ItemData(itme.getName(),itme.getName(),itme.getNotes()));
            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    });
        date.setText(getIntent().getStringExtra("date"));
        table.setText(getIntent().getStringExtra("table"));
        amount.setText(String.valueOf(getIntent().getFloatExtra("amount",0)));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setGravity(CENTER_HORIZONTAL);
                return view;
            }
        };

        lstView.setAdapter(arrayAdapter);

        Button paybtn = findViewById(R.id.payBtn);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference commandes = db.getReference("Commandes").child(idcomd);
                commandes.child("etat").setValue("Paid");
                finish();
            }
        });
    }
}