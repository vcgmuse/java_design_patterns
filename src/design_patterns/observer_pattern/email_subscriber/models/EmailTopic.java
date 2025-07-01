package design_patterns.observer_pattern.email_subscriber.models;

import java.util.ArrayList;
import java.util.List;

import design_patterns.observer_pattern.email_subscriber.interfaces.Observer;
import design_patterns.observer_pattern.email_subscriber.interfaces.Subject;

public class EmailTopic implements Subject {

    private List<Observer> observerList;
    private String message; 

    public EmailTopic() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Null Object / Observer cannot be registered.");
        }
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void unRegister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(this); 
        }
    }

    @Override
    public String getUpdate(Observer observer) {
        return this.message;
    }

    public void postMessage(String message) {
        System.out.println("Message posted to topic: " + message);
        this.message = message; // Update the state
        notifyObservers();      // Notify all registered observers
    }
}