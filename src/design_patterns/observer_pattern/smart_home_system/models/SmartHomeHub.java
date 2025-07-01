package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;
import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.Observer;
import design_patterns.observer_pattern.smart_home_system.interfaces.Subject;


import java.util.ArrayList;
import java.util.List;

public class SmartHomeHub implements Subject {
    private List<Observer> observers; // The list of registered smart devices

    public SmartHomeHub() {
        this.observers = new ArrayList<>(); // Initialize the list of observers
        System.out.println("SmartHomeHub initialized and ready to manage observers.");
    }

    @Override
    public void register(Observer observer) {
        if (!observers.contains(observer)) { // Prevent duplicate registrations
            observers.add(observer);
            System.out.println("Hub: Registered observer -> " + observer.getClass().getSimpleName() + " (" + observer.toString() + ")");
        } else {
            System.out.println("Hub: Observer already registered -> " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void unRegister(Observer observer) {
        if (observers.remove(observer)) { // Remove observer if present
            System.out.println("Hub: Unregistered observer -> " + observer.getClass().getSimpleName() + " (" + observer.toString() + ")");
        } else {
            System.out.println("Hub: Observer not found for unregistration -> " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void notifyObservers(SmartHomeEvent event) {
        System.out.println("\n--- Hub: Notifying observers about event: " + event.getEventType().name() + " at " + event.getLocation() + " ---");
        // Iterate through all registered observers and call their update method
        for (Observer observer : observers) {
            observer.update(event);
        }
        System.out.println("--- Hub: Notification complete ---");
    }

    /**
     * Public method to explicitly publish a pre-constructed SmartHomeEvent.
     * This can be used internally or by other components that create full event objects.
     * @param event The SmartHomeEvent to publish.
     */
    public void publishEvent(SmartHomeEvent event) {
        System.out.println("\nHub: Publishing event directly: " + event.getEventType().name() + " at " + event.getLocation());
        notifyObservers(event); // Delegates to the core notification logic
    }

    // --- Convenience Methods to Trigger Specific Smart Home Events ---
    // These methods simplify creating and publishing events without directly constructing payloads/events in Main.

    /**
     * Simulates a temperature change event from a sensor.
     * @param location The location where the temperature change occurred.
     * @param newTemp The new temperature reading.
     * @param unit The unit of temperature (e.g., "Celsius", "Fahrenheit").
     */
    public void triggerTemperatureChange(String location, double newTemp, String unit) {
        System.out.println("\nHub: Triggering Temperature Change at " + location + " to " + newTemp + " " + unit);
        TemperaturePayload payload = new TemperaturePayload(newTemp, unit);
        // Corrected instantiation: Directly use SmartHomeEvent
        SmartHomeEvent event = new SmartHomeEvent(SmartHomeEventType.TEMPERATURE_CHANGE, location, payload);
        notifyObservers(event);
    }

    /**
     * Simulates a motion detection event from a motion sensor.
     * @param location The location where motion was detected.
     * @param intensity The intensity of the motion (e.g., "low", "medium", "high").
     */
    public void triggerMotionDetected(String location, String intensity) {
        System.out.println("\nHub: Triggering Motion Detected at " + location + " with intensity " + intensity);
        MotionPayload payload = new MotionPayload(intensity);
        // Corrected instantiation: Directly use SmartHomeEvent
        SmartHomeEvent event = new SmartHomeEvent(SmartHomeEventType.MOTION_DETECTED, location, payload);
        notifyObservers(event);
    }

    /**
     * Simulates a door status change event from a door/window sensor.
     * @param location The location of the door.
     * @param isOpen True if the door is now open, false if closed.
     */
    public void triggerDoorStatusChange(String location, boolean isOpen) {
        System.out.println("\nHub: Triggering Door Status Change at " + location + " to " + (isOpen ? "Open" : "Closed"));
        DoorStatusPayload payload = new DoorStatusPayload(isOpen);
        // Corrected instantiation: Directly use SmartHomeEvent
        SmartHomeEvent event = new SmartHomeEvent(SmartHomeEventType.DOOR_STATUS_CHANGE, location, payload);
        notifyObservers(event);
    }

    /**
     * Simulates a light command being issued (e.g., from a user interface or automation rule).
     * @param location The location of the light to command.
     * @param command The command (e.g., "turnOn", "turnOff", "toggle").
     * @param brightness The desired brightness (0-100).
     * @param color The desired color (e.g., "white", "blue").
     */
    public void triggerLightCommand(String location, String command, int brightness, String color) {
        System.out.println("\nHub: Triggering Light Command at " + location + ": '" + command + "' (Brightness: " + brightness + ", Color: " + color + ")");
        LightCommandPayload payload = new LightCommandPayload(command, brightness, color);
        // Corrected instantiation: Directly use SmartHomeEvent
        SmartHomeEvent event = new SmartHomeEvent(SmartHomeEventType.LIGHT_COMMAND, location, payload);
        notifyObservers(event);
    }

    // You can add more trigger methods here for other custom event types as needed.
}