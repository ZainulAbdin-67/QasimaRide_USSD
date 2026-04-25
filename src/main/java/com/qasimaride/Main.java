package com.qasimaride;

import com.qasimaride.util.MenuHandler;

/**
 * Main entry point for QasimaRide USSD System.
 * Initializes the system with sample data and starts the menu.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   Welcome to QasimaRide USSD System");
        System.out.println("║   Rickshaw Management for Hyderabad");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Initialize the stand manager
        StandManager standManager = new StandManager();

        // Pre-populate with sample data
        initializeSampleData(standManager);

        // Start the menu handler
        MenuHandler menuHandler = new MenuHandler(standManager);
        menuHandler.showMainMenu();
    }

    /**
     * Initialize sample data for demonstration.
     */
    private static void initializeSampleData(StandManager standManager) {
        // Sample Drivers
        Driver driver1 = new Driver("D001", "Ahmed Khan", "9876543210", "TS-01-AB-1234", "DL-001", "CHARMINAR");
        Driver driver2 = new Driver("D002", "Rahul Sharma", "9876543211", "TS-01-AB-1235", "DL-002", "SECUNDERABAD");
        Driver driver3 = new Driver("D003", "Farah Ali", "9876543212", "TS-01-AB-1236", "DL-003", "GACHIBOWLI");

        standManager.registerDriver(driver1);
        standManager.registerDriver(driver2);
        standManager.registerDriver(driver3);

        // Sample Passengers
        Passenger passenger1 = new Passenger("P001", "Zainul Abdin", "8765432100");
        Passenger passenger2 = new Passenger("P002", "Priya Patel", "8765432101");

        standManager.registerPassenger(passenger1);
        standManager.registerPassenger(passenger2);

        System.out.println("✓ Sample data loaded successfully!");
        System.out.println("  Drivers: 3 registered");
        System.out.println("  Passengers: 2 registered\n");
    }
}