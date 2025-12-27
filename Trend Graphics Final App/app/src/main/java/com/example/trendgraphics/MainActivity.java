package com.example.trendgraphics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    TextInputEditText edEmail, edPassword;
    TextInputLayout l1, l2;
    Button btnLogin, btnSignup;

    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "LoginSession";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String USER_NAME = "username";
    private static final String USER_EMAIL = "useremail";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // If user is already logged in, redirect to Home
        if (sharedPreferences.getBoolean(IS_LOGGED_IN, false)) {
            navigateToHome();
        }

        l1 = findViewById(R.id.et_full_name);
        l2 = findViewById(R.id.et_mobile);
        edEmail = findViewById(R.id.et_email);
        edPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_submit);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, signup.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            String userEmail = edEmail.getText().toString().trim();
            String userPassword = edPassword.getText().toString().trim();

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                checkUser(userEmail, userPassword);
            }
        });
    }

    private void checkUser(String userEmail, String userPassword) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("useremail").equalTo(userEmail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);
                        String username = userSnapshot.child("username").getValue(String.class);
                        String email = userSnapshot.child("useremail").getValue(String.class);

                        if (passwordFromDB != null && passwordFromDB.equals(userPassword)) {
                            // Save login session in SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(IS_LOGGED_IN, true);
                            editor.putString(USER_NAME, username);
                            editor.putString(USER_EMAIL, email);
                            editor.apply();

                            navigateToHome();
                        } else {
                            edPassword.setError("Invalid Password");
                            edPassword.requestFocus();
                        }
                    }
                } else {
                    edEmail.setError("User does not exist");
                    edEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(MainActivity.this, select.class); // Your next activity
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkChangeListener, new android.content.IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListener);
    }
}
