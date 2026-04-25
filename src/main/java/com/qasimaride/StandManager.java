package com.qasimaride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * StandManager class manages all rickshaw stands and coordinates ride matching.
 * Implements Single Responsibility Principle - manages stand operations and ride allocation.
 */
public class StandManager {
    private HashMap<String, RickshawStand> stands;
    private ArrayList<Driver> allDrivers;
    private ArrayList<Passenger> allPassengers;

    public StandManager() {
        this.stands = new HashMap<>();
        this.allDrivers = new ArrayList<>();
        this.allPassengers = new ArrayList<>();
        initializeStands();
    }

    // Initialize default stands
    private void initializeStands() {
        stands.put("CHARMINAR", new RickshawStand("CHARMINAR", "Hyderabad Old City"));
        stands.put("SECUNDERABAD", new RickshawStand("SECUNDERABAD", "Secunderabad Railway Station"));
        stands.put("GACHIBOWLI", new RickshawStand("GACHIBOWLI", "HITEC City"));
    }

    // Add a driver to the system
    public void registerDriver(Driver driver) {
        if (driver != null && !allDrivers.contains(driver)) {
            allDrivers.add(driver);
            String stand = driver.getCurrentStand();
            if (stands.containsKey(stand)) {
                stands.get(stand).addDriver(driver);
            }
        }
    }

    // Add a passenger to the system
    public void registerPassenger(Passenger passenger) {
        if (passenger != null && !allPassengers.contains(passenger)) {
            allPassengers.add(passenger);
        }
    }

    // Request a ride (Add passenger to queue)
    public void requestRide(Passenger passenger, String standName, String source, String destination) {
        if (!stands.containsKey(standName)) {
            System.out.println("✗ Stand not found!");
            return;
        }

        passenger.setSourceLocation(source);
        passenger.setDestinationLocation(destination);
        passenger.setActiveRide(true);

        stands.get(standName).addPassengerToQueue(passenger);
    }

    // Allocate next available driver to passenger
    public boolean allocateRide(String standName) {
        if (!stands.containsKey(standName)) {
            System.out.println("✗ Stand not found!");
            return false;
        }

        RickshawStand stand = stands.get(standName);
        Passenger passenger = stand.peekNextPassenger();

        if (passenger == null) {
            System.out.println("✗ No passengers waiting at " + standName);
            return false;
        }

        ArrayList<Driver> availableDrivers = stand.getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            System.out.println("✗ No available drivers at " + standName);
            return false;
        }

        // Allocate the first available driver (FIFO logic)
        Driver allocatedDriver = availableDrivers.get(0);
        allocatedDriver.setAvailable(false);

        // Remove passenger from queue
        stand.getNextPassenger();

        System.out.println("\n✓ RIDE ALLOCATED!");
        System.out.println("  Passenger: " + passenger.getName());
        System.out.println("  Driver: " + allocatedDriver.getName());
        System.out.println("  Vehicle: " + allocatedDriver.getVehicleNumber());
        System.out.println("  Route: " + passenger.getSourceLocation() + " → " + passenger.getDestinationLocation());

        return true;
    }

    // Complete a ride and mark driver as available
    public void completeRide(Driver driver) {
        driver.setAvailable(true);
        System.out.println("✓ Ride completed. Driver " + driver.getName() + " is now available.");
    }

    // Get a specific stand
    public RickshawStand getStand(String standName) {
        return stands.get(standName);
    }

    // Get all stands
    public Map<String, RickshawStand> getAllStands() {
        return stands;
    }

    // Display system status
    public void displaySystemStatus() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   QASIMARIDE SYSTEM STATUS");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Total Drivers: " + allDrivers.size());
        System.out.println("Total Passengers: " + allPassengers.size());
        System.out.println("\nStand Details:");
        for (RickshawStand stand : stands.values()) {
            stand.displayStandStatus();
        }
    }

    // Get driver by ID
    public Driver getDriverById(String driverId) {
        for (Driver driver : allDrivers) {
            if (driver.getUserId().equals(driverId)) {
                return driver;
            }
        }
        return null;
    }

    // Get passenger by ID
    public Passenger getPassengerById(String passengerId) {
        for (Passenger passenger : allPassengers) {
            if (passenger.getUserId().equals(passengerId)) {
                return passenger;
            }
        }
        return null;
    }
}