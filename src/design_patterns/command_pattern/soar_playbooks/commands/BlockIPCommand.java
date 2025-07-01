// Path: design_patterns/command_pattern/soar_playbooks/commands/BlockIPCommand.java
package design_patterns.command_pattern.soar_playbooks.commands;

import design_patterns.command_pattern.soar_playbooks.interfaces.SecurityActionCommand;

public class BlockIPCommand implements SecurityActionCommand {
    private String ipAddress;
    // private FirewallAPI firewall; // In a real scenario, this would be a reference to the actual API client

    public BlockIPCommand(String ipAddress /*, FirewallAPI firewall */) {
        this.ipAddress = ipAddress;
        // this.firewall = firewall;
    }

    @Override
    public boolean execute() {
        // Simulate blocking the IP via a firewall API
        // if (firewall.addBlockRule(ipAddress)) {
        System.out.println("Executing: Blocking IP " + ipAddress);
        return true; // Assume success for demonstration
        // } else { return false; }
    }

    @Override
    public boolean rollback() {
        // Simulate unblocking the IP
        // if (firewall.removeBlockRule(ipAddress)) {
        System.out.println("Rolling back: Unblocking IP " + ipAddress);
        return true; // Assume success for demonstration
        // } else { return false; }
    }

    @Override
    public String getDescription() {
        return "Block IP " + ipAddress;
    }
}