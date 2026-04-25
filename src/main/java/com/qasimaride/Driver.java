package com.qasimaride;

/**
 * Driver class extending User.
 * Represents a rickshaw driver in the system.
 */
public class Driver extends User {
    private String vehicleNumber;
    private String licenseNumber;
    private boolean isAvailable;
    private String currentStand;

    public Driver(String userId, String name, String phoneNumber, 
                  String vehicleNumber, String licenseNumber, String currentStand) {
        super(userId, name, phoneNumber, "Driver");
        this.vehicleNumber = vehicleNumber;
        this.licenseNumber = licenseNumber;
        this.isAvailable = true;
        this.currentStand = currentStand;
    }

    // Getters and Setters
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCurrentStand() {
        return currentStand;
    }

    public void setCurrentStand(String currentStand) {
        this.currentStand = currentStand;
    }

    // Polymorphic method implementation
    @Override
    public void displayProfile() {
        System.out.println("\n=== DRIVER PROFILE ===");
        System.out.println("Driver ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Vehicle: " + vehicleNumber);
        System.out.println("License: " + licenseNumber);
        System.out.println("Status: " + (isAvailable ? "AVAILABLE" : "BUSY"));
        System.out.println("Current Stand: " + currentStand);
        System.out.println("====================");
    }

    public String getStatus() {
        return isAvailable ? "AVAILABLE" : "BUSY";
    }
}