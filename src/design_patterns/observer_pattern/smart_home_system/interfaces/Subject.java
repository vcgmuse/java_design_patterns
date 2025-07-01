package design_patterns.observer_pattern.smart_home_system.interfaces;

import design_patterns.observer_pattern.smart_home_system.events.SmartHomeEvent;

public interface Subject {
	public void register(Observer observer);
	public void unRegister(Observer observer);
	public void notifyObservers(SmartHomeEvent event);
}
