// Path: design_patterns/command_pattern/soar_playbooks/engine/PlaybookEngine.java
package design_patterns.command_pattern.soar_playbooks.engine;

import design_patterns.command_pattern.soar_playbooks.interfaces.SecurityActionCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlaybookEngine {
    private List<SecurityActionCommand> playbookSteps;
    private Stack<SecurityActionCommand> executedCommands; // History for potential rollback

    public PlaybookEngine() {
        this.playbookSteps = new ArrayList<>();
        this.executedCommands = new Stack<>();
    }

    public void addPlaybookStep(SecurityActionCommand command) {
        this.playbookSteps.add(command);
    }

    public void runPlaybook() {
        System.out.println("\n--- Running SOAR Playbook ---");
        for (SecurityActionCommand command : playbookSteps) {
            System.out.println("Attempting to execute: " + command.getDescription());
            if (command.execute()) {
                executedCommands.push(command); // Push to history only if execution was successful
                System.out.println("  -> SUCCESS");
            } else {
                System.err.println("  -> FAILED! Initiating Rollback for previous successful steps...");
                // On failure, attempt to rollback all previously successful commands in reverse order
                rollbackPlaybook();
                System.err.println("--- Playbook Run ABORTED ---");
                return; // Stop the playbook run immediately
            }
        }
        System.out.println("--- SOAR Playbook Completed Successfully ---");
    }

    public void rollbackPlaybook() {
        System.out.println("\n--- Initiating Playbook Rollback ---");
        // Rollback commands in reverse order of execution
        while (!executedCommands.isEmpty()) {
            SecurityActionCommand command = executedCommands.pop();
            System.out.println("Attempting to roll back: " + command.getDescription());
            if (command.rollback()) {
                System.out.println("  -> Rollback SUCCESS");
            } else {
                // This is a critical situation! Rollback failed. Manual intervention is required.
                System.err.println("  -> Rollback FAILED for " + command.getDescription() + " (Manual intervention required!)");
            }
        }
        System.out.println("--- Playbook Rollback Completed ---");
    }
}