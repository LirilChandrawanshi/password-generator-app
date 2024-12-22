package com.example.password_generator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passwords")
public class Password {

    @Id
    private String id;  // MongoDB automatically generates the ID
    private String password;

    // Default constructor for MongoDB
    public Password() {}

    // Constructor with password parameter
    public Password(String password) {
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
