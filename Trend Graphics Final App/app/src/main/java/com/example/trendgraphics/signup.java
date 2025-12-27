package com.example.trendgraphics;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    TextInputEditText edEmail, edPassword;
    TextInputLayout l1, l2;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edEmail = findViewById(R.id.editTextEmail);
        edPassword = findViewById(R.id.editTextPassword);
        l1=findViewById(R.id.editTextEmail2);
        l2=findViewById(R.id.editTextPassword2);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = edEmail.getText().toString().trim();
                String userPassword = edPassword.getText().toString().trim();

                if (userEmail.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    saveUserToFirebase(userEmail, userPassword);
                }
            }
        });
    }

    private void saveUserToFirebase(String userEmail, String userPassword) {
        // Get the reference to the Firebase database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        // Create a new user object
        User newUser = new User(userEmail, userPassword);

        // Push the user data to the Firebase database
        reference.child(userEmail.replace(".", "_")).setValue(newUser) // Replace "." with "_" because Firebase keys can't contain "."
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(signup.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                        finish(); // Go back to Login Activity after successful sign-up
                    } else {
                        Toast.makeText(signup.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
