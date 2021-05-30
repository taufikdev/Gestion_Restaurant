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
            case R.id.viewBtn:
                Intent i1 = new Intent(this, Commands.class);
                //cmdtype :1 =>orders ready.
                i1.putExtra("cmdtype",1);
                startActivity(i1);
                break;
            case R.id.payBtn:
                //startActivity(new Intent(this, Payment.class));
                Intent i = new Intent(this, Commands.class);
                //cmdtype :2 =>orders served.
                i.putExtra("cmdtype",2);
                startActivity(i);
                break;
        }
    }
}