// Path: com/example/undoredo/Command.java
package design_patterns.command_pattern.undoredo.interfaces;

public interface Command {
    void execute();
    void undo();
}