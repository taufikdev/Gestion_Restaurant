package com.aaexample.restaurant;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
        rec = findViewById(R.id.recycle);
        s1 = getResources().getStringArray(R.array.categories);
        MyAdapter myAdapter = new MyAdapter(this,s1,images);
        rec.setAdapter(myAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }
}
