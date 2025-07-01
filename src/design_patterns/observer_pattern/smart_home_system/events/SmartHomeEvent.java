package design_patterns.observer_pattern.smart_home_system.events;

import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;

public class SmartHomeEvent {
	private SmartHomeEventType eventType;
	private String location;
	private EventPayload payload;
	public SmartHomeEvent(SmartHomeEventType eventType, String location, EventPayload payload) {
		this.eventType = eventType;
		this.location = location;
		this.payload = payload;
	}
	public SmartHomeEventType getEventType() {
		return eventType;
	}
	public String getLocation() {
		return location;
	}
	public EventPayload getPayload() {
		return payload;
	}
	@Override
	public String toString() {
		return "SmartHomeEvent [eventType=" + eventType + ", location=" + location + ", payload=" + payload + "]";
	}
}
