package com.qasimaride;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * RickshawStand class manages drivers and passengers at a specific stand.
 * Uses Queue (FIFO) for passenger waiting list and ArrayList for drivers.
 */
public class RickshawStand {
    private String standName;
    private String location;
    private ArrayList<Driver> driversAtStand;
    private Queue<Passenger> passengerQueue; // FIFO queue for waiting passengers

    public RickshawStand(String standName, String location) {
        this.standName = standName;
        this.location = location;
        this.driversAtStand = new ArrayList<>();
        this.passengerQueue = new LinkedList<>();
    }

    // Getters
    public String getStandName() {
        return standName;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Driver> getDriversAtStand() {
        return driversAtStand;
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    // Add a driver to the stand
    public void addDriver(Driver driver) {
        if (driver != null && !driversAtStand.contains(driver)) {
            driversAtStand.add(driver);
            System.out.println("✓ Driver " + driver.getName() + " added to " + standName);
        }
    }

    // Remove a driver from the stand
    public void removeDriver(Driver driver) {
        if (driversAtStand.remove(driver)) {
            System.out.println("✓ Driver " + driver.getName() + " removed from " + standName);
        }
    }

    // Add a passenger to the waiting queue
    public void addPassengerToQueue(Passenger passenger) {
        if (passenger != null) {
            passengerQueue.add(passenger);
            System.out.println("✓ Passenger " + passenger.getName() + " added to queue at " + standName);
        }
    }

    // Get the next passenger in queue (FIFO)
    public Passenger getNextPassenger() {
        return passengerQueue.poll();
    }

    // Peek at the next passenger without removing
    public Passenger peekNextPassenger() {
        return passengerQueue.peek();
    }

    // Get available drivers (Single Responsibility)
    public ArrayList<Driver> getAvailableDrivers() {
        ArrayList<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driversAtStand) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    // Get queue length
    public int getQueueLength() {
        return passengerQueue.size();
    }

    // Get total drivers at stand
    public int getTotalDrivers() {
        return driversAtStand.size();
    }

    // Display stand status
    public void displayStandStatus() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     " + standName + " - " + location);
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Total Drivers: " + getTotalDrivers());
        System.out.println("Available Drivers: " + getAvailableDrivers().size());
        System.out.println("Passengers Waiting: " + getQueueLength());
    }
}