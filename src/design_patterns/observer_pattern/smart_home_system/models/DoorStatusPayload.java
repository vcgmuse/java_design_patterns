package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;

public class DoorStatusPayload implements EventPayload{
	
	private boolean isOpen;
	
	public DoorStatusPayload(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public String getPayloadType() {
		// TODO Auto-generated method stub
		return SmartHomeEventType.DOOR_STATUS_CHANGE.name();
	}

}
