// Path: design_patterns/command_pattern/soar_playbooks/commands/SendAlertCommand.java
package design_patterns.command_pattern.soar_playbooks.commands;

import design_patterns.command_pattern.soar_playbooks.interfaces.SecurityActionCommand;

public class SendAlertCommand implements SecurityActionCommand {
    private String recipient;
    private String message;
    // private MessagingAPI messenger; // In a real scenario, this would be a reference to a messaging API client

    public SendAlertCommand(String recipient, String message /*, MessagingAPI messenger */) {
        this.recipient = recipient;
        this.message = message;
        // this.messenger = messenger;
    }

    @Override
    public boolean execute() {
        // Simulate sending an alert
        // if (messenger.sendMessage(recipient, message)) {
        System.out.println("Executing: Sending Alert to " + recipient + ": '" + message + "'");
        return true; // Assume success for demonstration
        // } else { return false; }
    }

    @Override
    public boolean rollback() {
        // It's often impossible to "unsend" an alert.
        // In a real SOAR, this might involve sending a follow-up "false positive" or "resolved" alert,
        // or simply logging that the initial alert might need manual verification.
        System.out.println("Rolling back: Cannot 'unsend' alert, but logging that this alert was part of a rolled-back playbook.");
        return true; // Indicate that the rollback step completed (even if it's just logging)
    }

    @Override
    public String getDescription() {
        return "Send Alert to " + recipient;
    }
}