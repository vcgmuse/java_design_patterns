package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;

public class TemperaturePayload  implements EventPayload{
	
	private double newTemperature;
	private String unit;
	
	public TemperaturePayload(double newTemperature, String unit) {
		this.newTemperature = newTemperature;
		this.unit = unit;
	}

	public double getNewTemperature() {
		return newTemperature;
	}

	public String getUnit() {
		return unit;
	}

	@Override
	public String getPayloadType() {
		return SmartHomeEventType.TEMPERATURE_CHANGE.name();
	}

}
