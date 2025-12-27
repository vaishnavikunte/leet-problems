package com.example.trendgraphics;

 public class Feedback {
    private String fullName;
    private String mobileNumber;
    private float rating;

    // Empty constructor required for Firebase
    public Feedback() {
    }

    public Feedback(String fullName, String mobileNumber, float rating) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.rating = rating;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public float getRating() {
        return rating;
    }
}

