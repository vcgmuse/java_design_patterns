// Path: com/example/undoredo/AppendTextCommand.java
package design_patterns.command_pattern.undoredo;

import design_patterns.command_pattern.undoredo.interfaces.Command;

public class AppendTextCommand implements Command {
    private TextDocument document;
    private String textToAppend;
    private int appendedLength; // To know how much to delete for undo

    public AppendTextCommand(TextDocument document, String textToAppend) {
        this.document = document;
        this.textToAppend = textToAppend;
        this.appendedLength = textToAppend.length();
    }

    @Override
    public void execute() {
        document.append(textToAppend);
        System.out.println("AppendTextCommand: Executed. Appended: \"" + textToAppend + "\"");
    }

    @Override
    public void undo() {
        document.deleteLast(appendedLength);
        System.out.println("AppendTextCommand: Undone. Deleted last " + appendedLength + " characters.");
    }
}