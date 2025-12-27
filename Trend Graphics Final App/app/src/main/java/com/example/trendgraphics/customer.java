package com.example.trendgraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RatingBar;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class customer extends AppCompatActivity {

    private EditText etFullName, etMobile;
    private RatingBar ratingBar;
    private Button btnSubmit;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer); // Ensure this matches your XML file name

        // Initialize UI components
        etFullName = findViewById(R.id.et_full_name);
        etMobile = findViewById(R.id.et_mobile);
        ratingBar = findViewById(R.id.rating_bar);
        btnSubmit = findViewById(R.id.btn_submit);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("UserFeedback");

        // Set OnClickListener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFeedback();
            }
        });
    }

    private void saveFeedback() {
        String fullName = etFullName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        float rating = ratingBar.getRating(); // Get rating value

        // Validate inputs
        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Enter full name");
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Enter mobile number");
            return;
        }
        if (rating == 0) {
            Toast.makeText(this, "Please rate the app", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate unique ID for feedback
        String feedbackId = databaseReference.push().getKey();

        // Create feedback object
        Feedback feedback = new Feedback(fullName, mobile, rating);

        assert feedbackId != null;
        databaseReference.child(feedbackId).setValue(feedback)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(customer.this, "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                    // Clear inputs
                    etFullName.setText("");
                    etMobile.setText("");
                    ratingBar.setRating(0);
                })
                .addOnFailureListener(e -> Toast.makeText(customer.this, "Failed to submit feedback", Toast.LENGTH_SHORT).show());
    }
}
