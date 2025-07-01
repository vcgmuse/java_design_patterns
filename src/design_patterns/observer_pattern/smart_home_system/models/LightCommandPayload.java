package design_patterns.observer_pattern.smart_home_system.models;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEventType;
import design_patterns.observer_pattern.smart_home_system.interfaces.EventPayload;

public class LightCommandPayload implements EventPayload{
	
	private String command;
	private int brightness;
	private String color;
	
	public LightCommandPayload(String command, int brightness, String color) {
		this.command = command;
		this.brightness = brightness;
		this.color = color;
	}
	
	public String getCommand() {
		return command;
	}

	public int getBrightness() {
		return brightness;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String getPayloadType() {
		// TODO Auto-generated method stub
		return SmartHomeEventType.LIGHT_COMMAND.name();
	}

}
