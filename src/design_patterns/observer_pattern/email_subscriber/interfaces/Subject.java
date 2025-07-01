package design_patterns.observer_pattern.email_subscriber.interfaces;


public interface Subject {
	public void register(Observer observer);
	public void unRegister(Observer observer);
	public void notifyObservers();
	public String getUpdate(Observer observer);
}
