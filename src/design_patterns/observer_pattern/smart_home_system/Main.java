package design_patterns.observer_pattern.smart_home_system; // Or wherever you prefer your Main class to reside

import design_patterns.observer_pattern.smart_home_system.models.SmartHomeHub;
import design_patterns.observer_pattern.smart_home_system.models.SmartLight;
import design_patterns.observer_pattern.smart_home_system.models.SmartThermostat;
import design_patterns.observer_pattern.smart_home_system.models.SmartDoor;
import design_patterns.observer_pattern.smart_home_system.models.SecurityCamera;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Setting Up Smart Home System ---");

        // 1. Create the Smart Home Hub (The Subject)
        SmartHomeHub hub = new SmartHomeHub();
        System.out.println("SmartHomeHub created.");

        // 2. Create various Smart Devices (The Observers)
        SmartLight livingRoomLight = new SmartLight("Living Room Light", "Living Room");
        SmartLight kitchenLight = new SmartLight("Kitchen Light", "Kitchen");
        SmartThermostat bedroomThermostat = new SmartThermostat("Bedroom Thermostat", "Bedroom", 20.0); // Initial current temp
        SmartDoor frontDoor = new SmartDoor("Front Door", "Main Entrance", false); // Starts closed
        SecurityCamera hallwayCamera = new SecurityCamera("Hallway Camera", "Hallway");
        SmartLight bedroomLight = new SmartLight("Bedroom Light", "Bedroom");


        System.out.println("\n--- Registering Devices with the Hub ---");
        // 3. Register Observers with the Subject
        hub.register(livingRoomLight);
        hub.register(kitchenLight);
        hub.register(bedroomThermostat);
        hub.register(frontDoor);
        hub.register(hallwayCamera);
        hub.register(bedroomLight); // Register bedroom light too

        System.out.println("\n--- Simulating Smart Home Events ---");

        // --- Scenario 1: Temperature Change in Bedroom ---
        System.out.println("\n--- SIMULATION: Temperature Change in Bedroom ---");
        // Bedroom thermostat should react
        hub.triggerTemperatureChange("Bedroom", 25.5, "Celsius");
        // Other devices in different locations should ignore
        hub.triggerTemperatureChange("Living Room", 22.0, "Celsius"); // No thermostat here to react

        // --- Scenario 2: Motion Detected in Living Room ---
        System.out.println("\n--- SIMULATION: Motion Detected in Living Room ---");
        // Living room light should turn on
        hub.triggerMotionDetected("Living Room", "high");
        // Hallway camera should ignore (different location)
        hub.triggerMotionDetected("Hallway", "medium"); // Hallway camera reacts here
        // No light in kitchen reacts to living room motion
        hub.triggerMotionDetected("Kitchen", "low"); // Kitchen light reacts here

        // --- Scenario 3: Light Commands ---
        System.out.println("\n--- SIMULATION: Light Commands ---");
        // Explicitly turn off living room light (it's on from motion)
        hub.triggerLightCommand("Living Room", "turnOff", 0, "N/A");
        // Toggle kitchen light (it's on from motion)
        hub.triggerLightCommand("Kitchen", "toggle", 50, "white");
        // Turn on bedroom light
        hub.triggerLightCommand("Bedroom", "turnOn", 100, "warm white");


        // --- Scenario 4: Door Status Change ---
        System.out.println("\n--- SIMULATION: Door Status Change (Front Door) ---");
        // Front door changes state
        hub.triggerDoorStatusChange("Main Entrance", true); // Open the front door
        // Hallway camera might react if it's near main entrance or configured to (in our case it will)
        hub.triggerMotionDetected("Main Entrance", "medium"); // Simulate motion at open door

        System.out.println("\n--- SIMULATION: Door Status Change (Front Door - Close) ---");
        hub.triggerDoorStatusChange("Main Entrance", false); // Close the front door

        // --- Scenario 5: Setting Thermostat Target Temperature ---
        System.out.println("\n--- SIMULATION: Setting Thermostat Target Temperature ---");
        // This is not an 'event' in our current system, but a direct command to the device
        // If SmartThermostat had a remote control/API, this is how target would be set.
        // It's a method call, not an event published by the hub.
        bedroomThermostat.setTargetTemperature(21.0); // Directly call method on observer

        // Simulate another temperature change to see thermostat react to new target
        hub.triggerTemperatureChange("Bedroom", 20.5, "Celsius"); // Below new target, should activate heating

        System.out.println("\n--- SIMULATION: Device states after all events ---");
        System.out.println(livingRoomLight);
        System.out.println(kitchenLight);
        System.out.println(bedroomThermostat);
        System.out.println(frontDoor);
        System.out.println(hallwayCamera);
        System.out.println(bedroomLight);
    }
}