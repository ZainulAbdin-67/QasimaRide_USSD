package com.qasimaride.util;

import com.qasimaride.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

/**
 * MenuHandler class manages USSD menu navigation and user interactions.
 * Demonstrates separation of concerns (UI logic separated from business logic).
 */
public class MenuHandler {
    private StandManager standManager;
    private Scanner scanner;
    private Driver currentDriver;
    private Passenger currentPassenger;

    public MenuHandler(StandManager standManager) {
        this.standManager = standManager;
        this.scanner = new Scanner(System.in);
    }

    // Main menu
    public void showMainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   QASIMARIDE USSD (*789#)");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Driver Menu");
            System.out.println("2. Passenger Menu");
            System.out.println("3. Admin Status");
            System.out.println("4. Exit");
            System.out.print("\nEnter choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    driverMenu();
                    break;
                case 2:
                    passengerMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                case 4:
                    System.out.println("\n✓ Thank you for using QasimaRide!");
                    System.exit(0);
                default:
                    System.out.println("✗ Invalid choice. Try again.");
            }
        }
    }

    // Driver Menu
    private void driverMenu() {
        System.out.println("\n=== DRIVER MENU ===");
        System.out.print("Enter Driver ID (or create new): ");
        String driverId = scanner.nextLine().trim();

        currentDriver = standManager.getDriverById(driverId);

        if (currentDriver == null) {
            currentDriver = createDriver(driverId);
            if (currentDriver == null) return;
        }

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Driver Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Toggle Availability");
            System.out.println("3. View Current Stand");
            System.out.println("4. Complete a Ride");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    currentDriver.displayProfile();
                    break;
                case 2:
                    currentDriver.setAvailable(!currentDriver.isAvailable());
                    System.out.println("✓ Status updated to: " + currentDriver.getStatus());
                    break;
                case 3:
                    RickshawStand stand = standManager.getStand(currentDriver.getCurrentStand());
                    if (stand != null) stand.displayStandStatus();
                    break;
                case 4:
                    standManager.completeRide(currentDriver);
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("✗ Invalid choice.");
            }
        }
    }

    // Passenger Menu
    private void passengerMenu() {
        System.out.println("\n=== PASSENGER MENU ===");
        System.out.print("Enter Passenger ID (or create new): ");
        String passengerId = scanner.nextLine().trim();

        currentPassenger = standManager.getPassengerById(passengerId);

        if (currentPassenger == null) {
            currentPassenger = createPassenger(passengerId);
            if (currentPassenger == null) return;
        }

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Passenger Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Request a Ride");
            System.out.println("3. Check Queue Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    currentPassenger.displayProfile();
                    break;
                case 2:
                    requestRide();
                    break;
                case 3:
                    checkQueueStatus();
                    break;
                case 4:
                    inMenu = false;
                    break;
                default:
                    System.out.println("✗ Invalid choice.");
            }
        }
    }

    // Admin Menu
    private void adminMenu() {
        System.out.println("\n=== ADMIN PANEL ===");
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine().trim();

        if (!password.equals("admin123")) {
            System.out.println("✗ Invalid password!");
            return;
        }

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View System Status");
            System.out.println("2. Allocate Ride");
            System.out.println("3. View All Drivers");
            System.out.println("4. View All Passengers");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    standManager.displaySystemStatus();
                    break;
                case 2:
                    allocateRideAdmin();
                    break;
                case 3:
                    viewAllDrivers();
                    break;
                case 4:
                    viewAllPassengers();
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("✗ Invalid choice.");
            }
        }
    }

    // Request a ride
    private void requestRide() {
        System.out.println("\n--- Available Stands ---");
        standManager.getAllStands().keySet().forEach(System.out::println);

        System.out.print("Select Stand: ");
        String stand = scanner.nextLine().trim().toUpperCase();

        System.out.print("Source Location: ");
        String source = scanner.nextLine().trim();

        System.out.print("Destination: ");
        String destination = scanner.nextLine().trim();

        standManager.requestRide(currentPassenger, stand, source, destination);
    }

    // Check queue status
    private void checkQueueStatus() {
        System.out.print("Enter Stand Name: ");
        String standName = scanner.nextLine().trim().toUpperCase();

        RickshawStand stand = standManager.getStand(standName);
        if (stand != null) {
            stand.displayStandStatus();
            System.out.println("Your Position in Queue: " + (stand.getQueueLength() > 0 ? "Waiting" : "Available"));
        } else {
            System.out.println("✗ Stand not found!");
        }
    }

    // Allocate ride (Admin)
    private void allocateRideAdmin() {
        System.out.println("\n--- Available Stands ---");
        standManager.getAllStands().keySet().forEach(System.out::println);

        System.out.print("Select Stand: ");
        String stand = scanner.nextLine().trim().toUpperCase();

        standManager.allocateRide(stand);
    }

    // View all drivers
    private void viewAllDrivers() {
        System.out.println("\n=== ALL DRIVERS ===");
        for (Map.Entry<String, RickshawStand> entry : standManager.getAllStands().entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Driver driver : entry.getValue().getDriversAtStand()) {
                System.out.println("  - " + driver.getName() + " (" + driver.getStatus() + ")");
            }
        }
    }

    // View all passengers
    private void viewAllPassengers() {
        System.out.println("\n=== ALL PASSENGERS ===");
        for (Map.Entry<String, RickshawStand> entry : standManager.getAllStands().entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Passenger passenger : entry.getValue().getPassengerQueue()) {
                System.out.println("  - " + passenger.getName() + " (" + passenger.getRideStatus() + ")");
            }
        }
    }

    // Create a new driver
    private Driver createDriver(String driverId) {
        System.out.print("Driver Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Vehicle Number: ");
        String vehicleNum = scanner.nextLine().trim();
        System.out.print("License Number: ");
        String license = scanner.nextLine().trim();

        System.out.println("\nAvailable Stands: CHARMINAR, SECUNDERABAD, GACHIBOWLI");
        System.out.print("Select Stand: ");
        String stand = scanner.nextLine().trim().toUpperCase();

        Driver driver = new Driver(driverId, name, phone, vehicleNum, license, stand);
        standManager.registerDriver(driver);
        System.out.println("✓ Driver registered successfully!");
        return driver;
    }

    // Create a new passenger
    private Passenger createPassenger(String passengerId) {
        System.out.print("Passenger Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine().trim();

        Passenger passenger = new Passenger(passengerId, name, phone);
        standManager.registerPassenger(passenger);
        System.out.println("✓ Passenger registered successfully!");
        return passenger;
    }

    // Utility to get integer input safely
    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}