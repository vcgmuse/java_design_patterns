package design_patterns.observer_pattern.smart_home_system.interfaces;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;

public interface Observer {
	public void update(SmartHomeEvent event);
}
