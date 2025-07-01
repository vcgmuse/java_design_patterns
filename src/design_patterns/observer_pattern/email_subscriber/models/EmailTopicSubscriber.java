package design_patterns.observer_pattern.email_subscriber.models;

import design_patterns.observer_pattern.email_subscriber.interfaces.Observer;
import design_patterns.observer_pattern.email_subscriber.interfaces.Subject;


public class EmailTopicSubscriber implements Observer {
    private String name;
    
    public EmailTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) { 
        String message = subject.getUpdate(this); 

        if (message == null) {
            System.out.println(name + ": No new message on this topic (message is null).");
        } else {
            System.out.println(name + ": Retrieving message: \"" + message + "\"");
        }
    }
}