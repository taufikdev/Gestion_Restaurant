package com.aaexample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button txtBtn;
    EditText txtLogin,txtPass;
    DatabaseReference ref ;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtBtn = findViewById(R.id.button1);
        txtLogin = findViewById(R.id.editText1);
        txtPass = findViewById(R.id.editText2);
        user = new User();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");

        txtBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

//                String name=txtLogin.getText().toString();
//                String last_name="";
//                String mot_de_passe=txtPass.getText().toString();
//                String funtion="";
//                user.funtion=funtion;
//                user.last_name=last_name;
//                user.mot_de_passe=mot_de_passe;
//                user.name=name;
//                ref.push().setValue(user);
                //Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Tables.class);
                startActivity(i);
            }
        });

    }
}