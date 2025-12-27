package com.example.trendgraphics;



public class User {
    private String useremail;
    private String password;

    // Default constructor required for Firebase
    public User() {}

    public User(String useremail, String password) {
        this.useremail = useremail;
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
