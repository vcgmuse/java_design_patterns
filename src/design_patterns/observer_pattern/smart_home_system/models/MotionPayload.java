package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;

public class MotionPayload implements EventPayload{
	
	private String intensity;
	
	public MotionPayload(String intensity) {
		this.intensity = intensity;
	}

	public String getIntensity() {
		return intensity;
	}
	
	@Override
	public String getPayloadType() {
		// TODO Auto-generated method stub
		return SmartHomeEventType.MOTION_DETECTED.name();
	}
}
