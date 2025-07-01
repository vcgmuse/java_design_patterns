// Path: design_patterns/command_pattern/soar_playbooks/interfaces/SecurityActionCommand.java
package design_patterns.command_pattern.soar_playbooks.interfaces;

public interface SecurityActionCommand {
    /**
     * Executes the security action.
     * @return true if execution was successful, false otherwise.
     */
    boolean execute();

    /**
     * Reverses or rolls back the security action.
     * This is CRUCIAL for SOAR playbooks if an error occurs or a decision needs to be reverted.
     * @return true if rollback was successful, false otherwise.
     */
    boolean rollback(); // Often called 'undo' in general Command Pattern, but 'rollback' fits SOAR better.

    /**
     * Returns a description of the command for logging/auditing.
     */
    String getDescription();
}