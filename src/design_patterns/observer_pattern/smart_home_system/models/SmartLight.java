package design_patterns.observer_pattern.smart_home_system.models;

// Import all necessary classes for SmartLight to function correctly
import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;
import design_patterns.observer_pattern.smart_home_system.interfaces.Observer;
  // Added: Needed for MotionPayload
// You might also need other payload imports here if your light reacts to them,
// e.g., import design_patterns.observer_pattern.smart_home_system.payloads.DoorStatusPayload;

public class SmartLight implements Observer { // Correctly implements Observer
	private String name;
	private String location;
	private boolean isOn;

	public SmartLight(String name, String location) {
		this.name = name;
		this.location = location;
		this.isOn = false; // Light starts off by default
	}

	@Override // Indicates that this method is overriding an interface method
	public void update(SmartHomeEvent event) {

		// 1. Filter by Location: Only react if the event is in THIS light's location
		if (!this.location.equals(event.getLocation())) {
			return; // Event happened in a different location, so this light ignores it.
		}

		// Log the received event for debugging/tracing
		System.out.println(
				name +
				" (" + location + "): Received event: " +
				event.getEventType().name() + // Use .name() for enum string representation
				" at " + event.getLocation());

		EventPayload payload = event.getPayload(); // Get the generic payload from the event

		// 2. Process based on Event Type and Payload
		switch (event.getEventType()) {

			case MOTION_DETECTED:
				// Ensure the payload is of the expected type for MOTION_DETECTED
				if (payload instanceof MotionPayload) {
					MotionPayload motionPayload = (MotionPayload) payload; // Cast to specific payload type
					System.out.println("  " + name + ": Motion detected with intensity: " + motionPayload.getIntensity());
					if (!isOn) { // Only turn on if currently off
						turnOn();
						System.out.println("  " + name + ": Turning ON due to motion.");
					}
				} else {
					// Log an error if the payload type doesn't match the event type, indicating a potential bug
					System.err.println("  " + name + ": Error: MOTION_DETECTED event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			case LIGHT_COMMAND:
				// Ensure the payload is of the expected type for LIGHT_COMMAND
				if (payload instanceof LightCommandPayload) {
					LightCommandPayload lightCommand = (LightCommandPayload) payload; // Cast to specific payload type
					System.out.println("  " + name + ": Received light command: '" + lightCommand.getCommand() +
							"' Brightness: " + lightCommand.getBrightness() + ", Color: " + lightCommand.getColor() + ".");

					// React based on the specific command found in the payload
					if ("turnOn".equalsIgnoreCase(lightCommand.getCommand())) {
						turnOn();
						System.out.println("  " + name + ": Explicitly turning ON.");
					} else if ("turnOff".equalsIgnoreCase(lightCommand.getCommand())) {
						turnOff();
						System.out.println("  " + name + ": Explicitly turning OFF.");
					} else if ("toggle".equalsIgnoreCase(lightCommand.getCommand())) {
						toggleSwitch();
						System.out.println("  " + name + ": Toggling switch. Now: " + (isOn ? "ON" : "OFF") + ".");
					}
					// You could add logic here to apply brightness/color if the light supports it beyond just on/off/toggle
				} else {
					// Log an error if the payload type doesn't match the event type
					System.err.println("  " + name + ": Error: LIGHT_COMMAND event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			default:
				// Handle other event types if necessary, or simply log that they're not handled
				System.out.println("  " + name + ": Event type " + event.getEventType().name() + " not specifically handled by this light.");
				break;
		}
		// Always print the current state after processing the update for easy tracking
		System.out.println("  " + name + " current state: " + (isOn ? "ON" : "OFF"));
	}

	public void toggleSwitch() {
		this.isOn = !this.isOn; // Always use this.isOn for clarity
	}

	public void turnOn() {
		this.isOn = true;
	}

	public void turnOff() {
		this.isOn = false;
	}

	@Override
	public String toString() {
		return "SmartLight [name=" + name + ", location=" + location + ", isOn=" + isOn + "]";
	}
}