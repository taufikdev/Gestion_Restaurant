package com.aaexample.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "TAG_SIGNING";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        findViewById(R.id.loginBtn).setOnClickListener(this);

        // attach listener to detect sign in and sign out
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){
                Log.d(TAG, "Signed in " + user.getUid());
            }else{
                Log.d(TAG, "Currently signed out");
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null) mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.loginBtn) signIn();
    }

    public void signIn() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(),"empty fields",Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), Tables.class));
                } else
                    Toast.makeText(Login.this, "Signing failed", Toast.LENGTH_SHORT).show();
            });
        }
    }

    /* to log out from other Intent

    public void signOut() {
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    } */
}