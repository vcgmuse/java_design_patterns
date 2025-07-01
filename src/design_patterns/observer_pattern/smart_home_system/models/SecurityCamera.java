package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;
import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;
import design_patterns.observer_pattern.smart_home_system.interfaces.Observer;
 // Import specific payload for motion events


public class SecurityCamera implements Observer {
	private String name;
	private String location;
	private boolean isRecording; // True if the camera is currently recording

	public SecurityCamera(String name, String location) {
		this.name = name;
		this.location = location;
		this.isRecording = false; // Camera starts not recording by default
		System.out.println(name + " (" + location + ") initialized. State: " + (isRecording ? "Recording" : "Idle"));
	}

	@Override
	public void update(SmartHomeEvent event) {
		// 1. Filter by Location: Only react if the event is in THIS camera's location
		if (!this.location.equals(event.getLocation())) {
			return; // Event happened in a different location, so this camera ignores it.
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
					System.out.println("  " + name + ": Motion detected (Intensity: " + motionPayload.getIntensity() + ").");
					if (!isRecording) { // Only start recording if not already recording
						startRecording();
						System.out.println("  " + name + ": Initiating recording due to motion.");
					}
				} else {
					// Log an error if the payload type doesn't match the event type
					System.err.println("  " + name + ": Error: MOTION_DETECTED event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			case DOOR_STATUS_CHANGE:
				// Optional: React to a door opening in the same location (e.g., security monitoring)
				if (payload instanceof DoorStatusPayload) {
					DoorStatusPayload doorPayload = (DoorStatusPayload) payload;
					System.out.println("  " + name + ": Door status change detected: " + (doorPayload.isOpen() ? "OPEN" : "CLOSED"));
					if (doorPayload.isOpen() && !isRecording) { // If door opened and not already recording
						startRecording();
						System.out.println("  " + name + ": Initiating recording due to door opening.");
					}
					// You might add logic here to stop recording if the door closes after motion, etc.
				} else {
					System.err.println("  " + name + ": Error: DOOR_STATUS_CHANGE event with unexpected payload type: " + payload.getClass().getSimpleName());
				}
				break; // Don't forget the break!

			// You might add a case for CUSTOM_COMMAND to manually stop recording
			case CUSTOM_COMMAND:
				// This would depend on what your CustomCommandPayload contains.
				// For example, if it indicates a "stop recording" command.
				// if (payload instanceof CustomCommandPayload) { ... }
				System.out.println("  " + name + ": Received a CUSTOM_COMMAND. Further handling needed.");
				break;

			default:
				// Handle other event types if necessary, or simply log that they're not handled
				System.out.println("  " + name + ": Event type " + event.getEventType().name() + " not specifically handled by this camera.");
				break;
		}
		// Always print the current state after processing the update for easy tracking
		System.out.println("  " + name + " current state: " + (isRecording ? "Recording" : "Idle"));
	}

	// Methods to explicitly control recording
	public void startRecording() {
		if (!this.isRecording) {
			this.isRecording = true;
			System.out.println(name + " is now RECORDING.");
		} else {
			System.out.println(name + " is already RECORDING.");
		}
	}

	public void stopRecording() {
		if (this.isRecording) {
			this.isRecording = false;
			System.out.println(name + " has STOPPED RECORDING.");
		} else {
			System.out.println(name + " is already IDLE (not recording).");
		}
	}

	@Override
	public String toString() {
		return "SecurityCamera [name=" + name + ", location=" + location + ", isRecording=" + isRecording + "]";
	}
}