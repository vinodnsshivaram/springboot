package com.vinodshivaram.practice.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vinodshivaram.practice.springboot.support.Utilities;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@JsonPropertyOrder({ "userId", "username", "emailAddress", "phoneNumber", "id", "createdAt", "updatedAt" })
public class Registration extends BaseModel {

    private String userId;
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;

    public Registration() {
    }

    public Registration(String userId, String username, String password, String emailAddress, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void generateUserId() {
        this.userId = "ttttttttt" + Utilities.generateAlphaNumericStringOfLength(20).toLowerCase();
    }
}
