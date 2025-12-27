package com.example.trendgraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;


public class ordersaved extends AppCompatActivity {
    LottieAnimationView lottieCheckmark;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersaved);
                lottieCheckmark = findViewById(R.id.llottieCheckmark);
                lottieCheckmark.playAnimation();
            }
        }

