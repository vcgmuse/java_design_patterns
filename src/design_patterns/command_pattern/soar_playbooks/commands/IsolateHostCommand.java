// Path: design_patterns/command_pattern/soar_playbooks/commands/IsolateHostCommand.java
package design_patterns.command_pattern.soar_playbooks.commands;

import design_patterns.command_pattern.soar_playbooks.interfaces.SecurityActionCommand;

public class IsolateHostCommand implements SecurityActionCommand {
    private String hostname;
    // private EDRAPI edr; // In a real scenario, this would be a reference to the actual EDR API client

    public IsolateHostCommand(String hostname /*, EDRAPI edr */) {
        this.hostname = hostname;
        // this.edr = edr;
    }

    @Override
    public boolean execute() {
        // Simulate isolating the host via an EDR API
        // if (edr.isolateHost(hostname)) {
        System.out.println("Executing: Isolating Host " + hostname);
        return true; // Assume success for demonstration
        // } else { return false; }
    }

    @Override
    public boolean rollback() {
        // Simulate un-isolating the host
        // if (edr.unIsolateHost(hostname)) {
        System.out.println("Rolling back: Un-isolating Host " + hostname);
        return true; // Assume success for demonstration
        // } else { return false; }
    }

    @Override
    public String getDescription() {
        return "Isolate Host " + hostname;
    }
}