package design_patterns.observer_pattern.email_subscriber;





import design_patterns.observer_pattern.email_subscriber.interfaces.Observer;
import design_patterns.observer_pattern.email_subscriber.models.EmailTopic;
import design_patterns.observer_pattern.email_subscriber.models.EmailTopicSubscriber;

public class Main {

	public static void main(String[] args) {
		EmailTopic topic = new EmailTopic(); // Create the concrete Subject

		// Create concrete Observers
		Observer firstObserver = new EmailTopicSubscriber("First Observer");
		Observer secondObserver = new EmailTopicSubscriber("Second Observer");
		Observer thirdObserver = new EmailTopicSubscriber("Third Observer");

		// Register observers with the subject
		// The subject now knows about the observers.
		topic.register(firstObserver);
		topic.register(secondObserver);
		topic.register(thirdObserver);

		// Initial update before any message is posted (will likely show "No new message")
		// The observer needs to know *which* subject to ask for an update.
		// Since we've registered it, it's clear it's 'topic'.
		// In this new design, the 'update' method now requires a subject.
		// A direct call here would need 'topic' passed in, but typically
		// observers only get updated when the subject's state changes.
		// If you *want* an initial update like this, you would do:
		System.out.println("--- Initial Update Check (before message) ---");
		firstObserver.update(topic); // Pass the topic for initial pull

		System.out.println("\n--- Posting First Message ---");
		topic.postMessage("Hello subscribers! Welcome to the Observer Pattern!");

		// Unregister one observer to demonstrate dynamic behavior
		System.out.println("\n--- Unregistering Second Observer ---");
		topic.unRegister(secondObserver);

		System.out.println("\n--- Posting Second Message ---");
		topic.postMessage("New product launch next week!");

		// You can re-register observers later if needed
		// System.out.println("\n--- Re-registering Second Observer ---");
		// topic.register(secondObserver);
		// topic.postMessage("Welcome back, Second Observer!");
	}
}