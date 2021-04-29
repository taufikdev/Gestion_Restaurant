package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Tables extends AppCompatActivity {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
//        Button txtBtn = (Button) findViewById(R.id.btnCat);
//        txtBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.i("----","ok");
//                Intent i = new Intent(getApplicationContext(),Categories.class);
//                startActivity(i);
//            }
//        });
        fab = (FloatingActionButton) findViewById(R.id.btnCat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Categories.class);
                startActivity(i);
            }
        });
    }
}
