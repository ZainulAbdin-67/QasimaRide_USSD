package com.qasimaride;

/**
 * Passenger class extending User.
 * Represents a passenger requesting a ride.
 */
public class Passenger extends User {
    private String sourceLocation;
    private String destinationLocation;
    private boolean hasActiveRide;

    public Passenger(String userId, String name, String phoneNumber) {
        super(userId, name, phoneNumber, "Passenger");
        this.sourceLocation = null;
        this.destinationLocation = null;
        this.hasActiveRide = false;
    }

    // Getters and Setters
    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public boolean hasActiveRide() {
        return hasActiveRide;
    }

    public void setActiveRide(boolean hasActiveRide) {
        this.hasActiveRide = hasActiveRide;
    }

    // Polymorphic method implementation
    @Override
    public void displayProfile() {
        System.out.println("\n=== PASSENGER PROFILE ===");
        System.out.println("Passenger ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Active Ride: " + (hasActiveRide ? "YES" : "NO"));
        if (sourceLocation != null) {
            System.out.println("From: " + sourceLocation);
        }
        if (destinationLocation != null) {
            System.out.println("To: " + destinationLocation);
        }
        System.out.println("========================");
    }

    public String getRideStatus() {
        return hasActiveRide ? "IN RIDE" : "WAITING";
    }
}