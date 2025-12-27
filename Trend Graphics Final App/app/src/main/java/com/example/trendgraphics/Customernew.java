package com.example.trendgraphics;


public class Customernew {
    private String id, email, description, amount;

    public Customernew() {
    }

    public Customernew(String id, String email, String description, String amount) {
        this.id = id;
        this.email = email;
        this.description = description;
        this.amount = amount;
    }

    public String getEmail() { return email; }
    public String getDescription() { return description; }
    public String getAmount() { return amount; }
}
