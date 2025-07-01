// Path: com/example/undoredo/DeleteTextCommand.java
package design_patterns.command_pattern.undoredo;

import design_patterns.command_pattern.undoredo.interfaces.Command;

public class DeleteTextCommand implements Command {
    private TextDocument document;
    private int charsToDelete;
    private String deletedContent; // CRUCIAL: Stores what was deleted for undo

    public DeleteTextCommand(TextDocument document, int charsToDelete) {
        this.document = document;
        this.charsToDelete = charsToDelete;
        this.deletedContent = ""; // Initialize, will be set during execute
    }

    @Override
    public void execute() {
        deletedContent = document.deleteLast(charsToDelete); // Store the actual deleted content
        System.out.println("DeleteTextCommand: Executed. Deleted: \"" + deletedContent + "\"");
    }

    @Override
    public void undo() {
        document.append(deletedContent); // Append the previously deleted content
        System.out.println("DeleteTextCommand: Undone. Re-added: \"" + deletedContent + "\"");
    }
}