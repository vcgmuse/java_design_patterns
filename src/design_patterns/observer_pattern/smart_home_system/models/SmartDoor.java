package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;
import design_patterns.observer_pattern.smart_home_system.interfaces.Observer;


public class SmartDoor implements Observer {
	private String name;
	private String location;
	private boolean isOpen; // True if the door is open, false if closed

	public SmartDoor(String name, String location, boolean initialIsOpen) {
		this.name = name;
		this.location = location;
		this.isOpen = initialIsOpen;
		System.out.println(name + " (" + location + ") initialized. State: " + (isOpen ? "Open" : "Closed"));
	}

	@Override
	public void update(SmartHomeEvent event) {
		// 1. Filter by Location: Only react if the event is in THIS door's location
		if (!this.location.equals(event.getLocation())) {
			return; // Event happened in a different location, so this door ignores it.
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
			case DOOR_STATUS_CHANGE:
				// Ensure the payload is of the expected type for DOOR_STATUS_CHANGE
				if (payload instanceof DoorStatusPayload) {
					DoorStatusPayload doorPayload = (DoorStatusPayload) payload; // Cast to specific payload type
					boolean newStatus = doorPayload.isOpen();

					if (this.isOpen != newStatus) { // Only update if state has actually changed
						if (newStatus) {
							openDoor();
							System.out.println("  " + name + ": Status changed to OPEN.");
						} else {
							closeDoor();
							System.out.println("  " + name + ": Status changed to CLOSED.");
						}
					} else {
						System.out.println("  " + name + ": Status already " + (isOpen ? "OPEN" : "CLOSED") + ". No change.");
					}
				} else {
					// Log an error if the payload type doesn't match the event type
					System.err.println("  " + name + ": Error: DOOR_STATUS_CHANGE event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			case MOTION_DETECTED:
				// Optional: React to motion detected in the same location (e.g., for security)
				if (payload instanceof MotionPayload) {
					MotionPayload motionPayload = (MotionPayload) payload;
					System.out.println("  " + name + ": Motion detected near door (Intensity: " + motionPayload.getIntensity() + ").");
					if (this.isOpen) {
						System.out.println("  !!! ALERT: " + name + " is OPEN and MOTION detected!");
					}
				} else {
					System.err.println("  " + name + ": Error: MOTION_DETECTED event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			default:
				// Handle other event types if necessary, or simply log that they're not handled
				System.out.println("  " + name + ": Event type " + event.getEventType().name() + " not specifically handled by this door.");
				break;
		}
		// Always print the current state after processing the update for easy tracking
		System.out.println("  " + name + " current state: " + (isOpen ? "Open" : "Closed"));
	}

	// Methods to explicitly change door state
	public void openDoor() {
		this.isOpen = true;
	}

	public void closeDoor() {
		this.isOpen = false;
	}

	// Optional: Toggle method for convenience, similar to SmartLight
	public void toggleDoor() {
		this.isOpen = !this.isOpen;
		System.out.println("  " + name + ": Door toggled to " + (this.isOpen ? "OPEN" : "CLOSED"));
	}

	@Override
	public String toString() {
		return "SmartDoor [name=" + name + ", location=" + location + ", isOpen=" + isOpen + "]";
	}
}