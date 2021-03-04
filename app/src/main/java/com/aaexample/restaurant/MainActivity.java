package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button txtBtn = (Button) findViewById(R.id.textButton);
        txtBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("----","ok");
                Intent i = new Intent(getApplicationContext(),Categories.class);
                startActivity(i);
            }
        });
        Button txtBtn2 = (Button) findViewById(R.id.button);
        txtBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("----","ok");
                Intent i = new Intent(getApplicationContext(),Item.class);
                startActivity(i);
            }
        });
    }
}