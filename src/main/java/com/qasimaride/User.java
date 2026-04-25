package com.qasimaride;

/**
 * Abstract base class representing a User in the QasimaRide system.
 * Demonstrates Encapsulation and serves as the parent for inheritance.
 */
public abstract class User {
    private String userId;
    private String name;
    private String phoneNumber;
    private String userType; // "Driver" or "Passenger"

    public User(String userId, String name, String phoneNumber, String userType) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    // Getters and Setters (Encapsulation)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Abstract method for polymorphism
    public abstract void displayProfile();

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}