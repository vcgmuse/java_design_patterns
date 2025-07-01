// Path: com/example/undoredo/TextEditor.java
package design_patterns.command_pattern.undoredo;
import design_patterns.command_pattern.undoredo.interfaces.Command;

import java.util.Stack;

public class TextEditor {
    private TextDocument document;
    private Stack<Command> undoStack; // History of executed commands
    private Stack<Command> redoStack; // History of undone commands

    public TextEditor(TextDocument document) {
        this.document = document;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        System.out.println("TextEditor: Initialized.");
    }

    public void performAction(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        System.out.println("TextEditor: Action performed. Undo stack size: " + undoStack.size());
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            System.out.println("TextEditor: Undo performed. Undo stack size: " + undoStack.size() + ", Redo stack size: " + redoStack.size());
        } else {
            System.out.println("TextEditor: Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
            System.out.println("TextEditor: Redo performed. Undo stack size: " + undoStack.size() + ", Redo stack size: " + redoStack.size());
        } else {
            System.out.println("TextEditor: Nothing to redo.");
        }
    }

    // --- NEW: Clear History Method ---
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
        System.out.println("TextEditor: History cleared.");
    }

    public String getCurrentDocumentContent() {
        return document.getContent();
    }
}