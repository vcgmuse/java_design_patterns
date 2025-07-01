// Path: design_patterns/command_pattern/soar_playbooks/main/Main.java
package design_patterns.command_pattern.soar_playbooks.main;

import design_patterns.command_pattern.soar_playbooks.commands.BlockIPCommand;
import design_patterns.command_pattern.soar_playbooks.commands.IsolateHostCommand;
import design_patterns.command_pattern.soar_playbooks.commands.SendAlertCommand;
import design_patterns.command_pattern.soar_playbooks.engine.PlaybookEngine;
import design_patterns.command_pattern.soar_playbooks.interfaces.SecurityActionCommand; // For custom failing command

public class Main {
    public static void main(String[] args) {
        System.out.println("--- SOAR Playbook Demonstration ---");

        // Scenario 1: Successful Playbook Run
        System.out.println("\n===== Scenario 1: Successful Playbook =====");
        PlaybookEngine successfulEngine = new PlaybookEngine();
        successfulEngine.addPlaybookStep(new BlockIPCommand("1.2.3.4"));
        successfulEngine.addPlaybookStep(new IsolateHostCommand("server-web-01"));
        successfulEngine.addPlaybookStep(new SendAlertCommand("soc@example.com", "High severity alert: Malware detected on web-01."));
        successfulEngine.runPlaybook();

        // Scenario 2: Playbook with a Failing Step (and automatic rollback)
        System.out.println("\n===== Scenario 2: Playbook with a Failing Step =====");
        PlaybookEngine failingEngine = new PlaybookEngine();
        failingEngine.addPlaybookStep(new BlockIPCommand("5.6.7.8")); // This command will execute
        failingEngine.addPlaybookStep(new IsolateHostCommand("critical-db-01")); // This command will execute
        // Introduce a command that simulates failure
        failingEngine.addPlaybookStep(new SecurityActionCommand() {
            @Override public boolean execute() {
                System.out.println("Executing: Attempting a problematic action...");
                return false; // Simulate failure
            }
            @Override public boolean rollback() {
                System.out.println("Rolling back: Problematic action (no real state to revert).");
                return true;
            }
            @Override public String getDescription() { return "Problematic (Failing) Command"; }
        });
        failingEngine.addPlaybookStep(new SendAlertCommand("soc@example.com", "This alert will NOT be sent due to prior failure.")); // This command will NOT execute

        failingEngine.runPlaybook();
    }
}