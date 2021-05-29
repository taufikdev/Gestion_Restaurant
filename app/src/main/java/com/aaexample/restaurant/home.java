package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.takeBtn).setOnClickListener(this);
        findViewById(R.id.viewBtn).setOnClickListener(this);
        findViewById(R.id.payBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takeBtn: startActivity(new Intent(this, Tables.class)); break;
            case R.id.viewBtn: startActivity(new Intent(this, Commands.class)); break;
           // case R.id.payBtn: startActivity(new Intent(this, Payment.class)); break;
        }
    }
}