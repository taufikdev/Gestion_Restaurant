package com.aaexample.restaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aaexample.restaurant.classes.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public String keyy="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        /* Button txtBtn = (Button) findViewById(R.id.signUpBtn);
        txtBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("----","ok");
                Intent i = new Intent(getApplicationContext(),Categories.class);
                startActivity(i);
            }
        }); */
        EditText txt,txt2;
        txt = findViewById(R.id.email);
        txt2 = findViewById(R.id.password);
        Button txtBtn2 = (Button) findViewById(R.id.loginBtn);
        txtBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Log.i("----","ok");

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                //add users
                /*DatabaseReference users = db.getReference("Users");
                String id = users.push().getKey();
                User us = new User(id,"taoufik","ed-darraz","serveur","123");
                users.child(id).setValue(us);*/
                //add items
                /*DatabaseReference items = db.getReference("Items");
                String id = items.push().getKey();
                Iteme it = new Iteme(id,"item1",11.0f,"catg1",R.drawable.pizza);
                items.child(id).setValue(it);

                id = items.push().getKey();
                Iteme it1 = new Iteme(id,"item2",50.0f,"catg2",R.drawable.pate);
                items.child(id).setValue(it1);

                id = items.push().getKey();
                Iteme it2 = new Iteme(id,"item3",64.0f,"catg3",R.drawable.sandwich);
                items.child(id).setValue(it2);
                */
                //add ingrediants
                /*DatabaseReference ingrds = db.getReference("Ingredients");
                String id = ingrds.push().getKey();
                Ingredient it1 = new Ingredient(id,"ingr1",R.drawable.taco);
                ingrds.child(id).setValue(it1);

                id = ingrds.push().getKey();
                Ingredient it2 = new Ingredient(id,"ingr2",R.drawable.panini);
                ingrds.child(id).setValue(it2);

                id = ingrds.push().getKey();
                Ingredient it3 = new Ingredient(id,"ingr3",R.drawable.taco);
                ingrds.child(id).setValue(it3);*/
                //read user

                String user = txt.getText().toString();
                String psswd = txt2.getText().toString();

                if(user.isEmpty() || psswd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Champs vide",Toast.LENGTH_SHORT).show();
                }
                else{

                    Query checkUser = db.getReference("Users").orderByChild("name").equalTo(user);

                    checkUser.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            keyy = snapshot.getKey();
                        }
                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }

                    });
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                User user = snapshot.child(keyy).getValue(User.class);
                                if(user.getMot_de_passe().equals(psswd)){
                                    Intent i = new Intent(getApplicationContext(),Item.class);
                                    startActivity(i);
                                }else {
                                    Toast.makeText(getApplicationContext(),"erreur mdp",Toast.LENGTH_LONG).show();
                                }

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"erreur!!",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}