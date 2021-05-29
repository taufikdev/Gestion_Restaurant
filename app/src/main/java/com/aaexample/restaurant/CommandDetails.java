package com.aaexample.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aaexample.restaurant.R;
import com.aaexample.restaurant.classes.Commande;
import com.aaexample.restaurant.classes.Iteme;
import com.aaexample.restaurant.classes.MyApplication;
import com.aaexample.restaurant.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommandDetails extends AppCompatActivity {

    private TextView orderId, serverName, tableNumber;
    private ListView lstItems;
    private String OrderId,TableNum;
    final ArrayList<ItemData> listItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_details);

        tableNumber = findViewById(R.id.txtTableNum);
        serverName = findViewById(R.id.txtServerName);
        lstItems = findViewById(R.id.lstvCmdItems);

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        OrderId = getIntent().getStringExtra("idcomd");
        TableNum = getIntent().getStringExtra("table");
        DatabaseReference ref = db.getReference("Commandes").child(OrderId).child("Items");

    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot itm:snapshot.getChildren()){
                Iteme itme = itm.getValue(Iteme.class);
             //   Toast.makeText(getApplicationContext(),itme.getName(),Toast.LENGTH_LONG).show();
                listItems.add(new ItemData(itme.getName(),itme.getName(),itme.getNotes()));
            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


 /*       listItems.add(new ItemData("Pizza ","Margarita","dfg"));
        listItems.add(new ItemData("Pizza ","Margarita","dfg"));
        listItems.add(new ItemData("Pizza ","Margarita","dfg"));
        listItems.add(new ItemData("Pizza ","Margarita","dfg"));*/
        tableNumber.setText(TableNum);
        serverName.setText(MyApplication.gUser);


  //      tableNumber.setText("25");
//        serverName.setText("Ouharra Wafae");

        lstItems.setAdapter(new CustomAdapter(getApplicationContext(), listItems));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        orderId.setText(OrderId);
    }
}