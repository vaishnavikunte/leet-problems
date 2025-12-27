package com.example.trendgraphics;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class buy extends AppCompatActivity {

    TextInputEditText et1, et2, et3, et4;
    RadioGroup radioGroup;
    Button finish;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Customers");

        // Initialize views
        initializeViews();

        // Set click listener to save data
        finish.setOnClickListener(view -> saveCustomerData());

        // Navigate to order saved screen
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, ordersaved.class);
                startActivity(intent);
            }
        });
    }

    void initializeViews() {
        finish = findViewById(R.id.dataupload);
        et1 = findViewById(R.id.email2);
        et2 = findViewById(R.id.upi2);
        et3 = findViewById(R.id.req2);
        et4 = findViewById(R.id.sample2);
        radioGroup = findViewById(R.id.radiogroup);

        // Initially disable the button
        finish.setEnabled(false);

        // Add TextWatcher to all required fields
        et1.addTextChangedListener(LoginWatcher);
        et2.addTextChangedListener(LoginWatcher);
        et3.addTextChangedListener(LoginWatcher);
    }

    private void saveCustomerData() {
        String id = databaseReference.push().getKey();

        // Get the selected RadioButton value (Poster/Logo)
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedOption = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";

        Map<String, String> customerData = new HashMap<>();
        customerData.put("id", id);
        customerData.put("email", et1.getText().toString());
        customerData.put("upi", et2.getText().toString());
        customerData.put("req", et3.getText().toString());
        customerData.put("sample", et4.getText().toString());
        customerData.put("designType", selectedOption);  // Add selected radio button to Firebase

        databaseReference.child(id).setValue(customerData)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Order Saved Successfully!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show());
    }

    TextWatcher LoginWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = et1.getText().toString().trim();
            String upi = et2.getText().toString().trim();
            String req = et3.getText().toString().trim();

            // Enable the button only if et1, et2, and et3 are filled
            finish.setEnabled(!email.isEmpty() && !upi.isEmpty() && !req.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };
}
