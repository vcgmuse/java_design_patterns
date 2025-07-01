package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;
import design_patterns.observer_pattern.smart_home_system.interfaces.Observer; // <-- New Import

public class SmartThermostat implements Observer { // <-- Implement Observer
	private String name;
	private String location;
	private double currentTemp;
	private double targetTemp;
    private boolean heatingOn = false; // Track HVAC state
    private boolean coolingOn = false; // Track HVAC state

	public SmartThermostat(String name, String location, double initialCurrentTemp) { // Renamed parameter for clarity
		this.name = name;
		this.location = location;
		this.currentTemp = initialCurrentTemp;
		this.targetTemp = 22.0; // Set a sensible default target temperature (e.g., 22.0 C)
		System.out.println(name + " (" + location + ") initialized. Current: " + String.format("%.1f", currentTemp) + ", Target: " + String.format("%.1f", targetTemp));
	}

	@Override // <-- Add @Override
	public void update(SmartHomeEvent event) {
		// 1. Filter by Location: Only react if the event is in THIS thermostat's location
		if (!this.location.equals(event.getLocation())) {
	        return; // Event happened in a different location, so this thermostat ignores it.
	    }

	    System.out.println(
	    		name +
	    		" (" + location + "): Received event: " +
	    		event.getEventType().name() + // Use .name() for enum string
	    		" at " + event.getLocation());

	    EventPayload payload = event.getPayload();

	    // 2. Process based on Event Type and Payload
	    switch (event.getEventType()) {
	    case TEMPERATURE_CHANGE:
	    	// Check if the payload is indeed a TemperaturePayload
	    	if (payload instanceof TemperaturePayload) {
                TemperaturePayload temperaturePayload = (TemperaturePayload) payload;
                double receivedTemp = temperaturePayload.getNewTemperature();
                // Optional: check temperaturePayload.getUnit() if you support different units

                System.out.println("  " + name + ": Sensor reading: " + String.format("%.1f", receivedTemp) + " degrees.");

                // Update the thermostat's internal current temperature
                // This is the core reaction to a TEMPERATURE_CHANGE event
                this.currentTemp = receivedTemp;

                // After updating the current temperature, regulate the HVAC
                regulateTemperature();

	    	} else {
	            System.err.println("  " + name + ": Error: TEMPERATURE_CHANGE event without TemperaturePayload.");
	        }
	        break; // <-- IMPORTANT: Add break here!

	    // Add other cases if the thermostat reacts to other event types (e.g., a direct command to set target temp)
	    case CUSTOM_COMMAND:
	        // Example: If a custom command payload contains a new target temp
	        // This would involve another specific payload type (e.g., SetTargetTempPayload)
	        // For now, we'll keep it simple and assume target temp is set via setTargetTemperature method.
	        System.out.println("  " + name + ": Received a CUSTOM_COMMAND. Further handling needed.");
	        break;

        default:
            System.out.println("  " + name + ": Event type " + event.getEventType().name() + " not specifically handled by thermostat.");
            break;
	    }
	    // Print current state after processing the update
	    System.out.println("  " + name + " current state: " + toString());
	}

	// Public method to set the target temperature (user's desired temp)
	public void setTargetTemperature(double newTargetTemp) { // Renamed for clarity
		if (this.targetTemp != newTargetTemp) {
			System.out.println(name + ": Target temperature changed from " + String.format("%.1f", this.targetTemp) + " to " + String.format("%.1f", newTargetTemp));
			this.targetTemp = newTargetTemp;
			regulateTemperature(); // Regulate immediately if target changes
		} else {
			System.out.println(name + ": Target temperature already " + String.format("%.1f", newTargetTemp) + ". No change.");
		}
	}

	// Internal method for thermostat to regulate its temperature based on current and target
	private void regulateTemperature() {
	    System.out.println("  " + name + ": Regulating... Current: " + String.format("%.1f", currentTemp) + ", Target: " + String.format("%.1f", targetTemp));
	    double hysteresis = 0.5; // Small threshold to avoid rapid on/off cycling

	    if (currentTemp < targetTemp - hysteresis) {
	        // Need to heat
	        if (!heatingOn) {
	            System.out.println("  " + name + ": Activating Heating.");
	            heatingOn = true;
	            coolingOn = false; // Ensure cooling is off if heating
	        }
	    } else if (currentTemp > targetTemp + hysteresis) {
	        // Need to cool
	        if (!coolingOn) {
	            System.out.println("  " + name + ": Activating Cooling.");
	            coolingOn = true;
	            heatingOn = false; // Ensure heating is off if cooling
	        }
	    } else {
	        // Within acceptable range, turn off heating/cooling
	        if (heatingOn || coolingOn) {
	            System.out.println("  " + name + ": Temperature within range. Deactivating HVAC.");
	            heatingOn = false;
	            coolingOn = false;
	        }
	    }
	}

	@Override
	public String toString() {
		String hvacStatus = "Idle";
		if (heatingOn) hvacStatus = "Heating";
		else if (coolingOn) hvacStatus = "Cooling";
		return "SmartThermostat [name=" + name + ", location=" + location +
				", currentTemp=" + String.format("%.1f", currentTemp) +
				", targetTemp=" + String.format("%.1f", targetTemp) +
				", HVAC Status: " + hvacStatus + "]";
	}
}