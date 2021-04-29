package com.aaexample.restaurant;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Categories extends AppCompatActivity {
    RecyclerView rec ;
    String s1[];
    int images[]={
            R.drawable.pizza,
            R.drawable.panini,
            R.drawable.pate,
            R.drawable.chicken,
            R.drawable.taco,
            R.drawable.sandwich,
            R.drawable.dessert
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Button txtBtn = (Button) findViewById(R.id.btnItem);
        txtBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("----","ok");
                Intent i = new Intent(getApplicationContext(),Item.class);
                startActivity(i);
            }
        });
        rec = findViewById(R.id.recycle);
        s1 = getResources().getStringArray(R.array.categories);
        MyAdapter myAdapter = new MyAdapter(this,s1,images);
        rec.setAdapter(myAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }
}
