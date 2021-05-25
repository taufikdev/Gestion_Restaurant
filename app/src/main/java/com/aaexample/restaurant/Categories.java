package com.aaexample.restaurant;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.aaexample.restaurant.classes.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Categories extends AppCompatActivity {
    private ArrayList<ListItem> catItems;
    ListView catViews ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        catViews = findViewById(R.id.lstView);
        catItems = new ArrayList<ListItem>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        DatabaseReference ref = db.getReference("categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot c:snapshot.getChildren()
                ) {
                    Category cat = c.getValue(Category.class);
                    Context context = getApplicationContext();
                    int idimg = context.getResources().getIdentifier(cat.getImage(), "drawable", context.getPackageName());
                    catItems.add(new ListItem(cat.getName(),idimg));
                }
                catViews.setAdapter(new MyAdapter(getApplicationContext(), catItems));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        catViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem cat = catItems.get(position);
                Intent i = new Intent(getApplicationContext(), Item.class);
              //  String Label = getIntent().getStringExtra("label");
                String idcmd = getIntent().getStringExtra("idcmd");
              //  i.putExtra("label", Label);
                i.putExtra("idcmd",idcmd);
                //get the name of category
                i.putExtra("cat",cat.getmTableNumber());
                startActivity(i);
            }
        });

    }
  }
