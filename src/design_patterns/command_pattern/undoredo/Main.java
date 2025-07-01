// Path: com/example/undoredo/Main.java
package design_patterns.command_pattern.undoredo;
import design_patterns.command_pattern.undoredo.interfaces.Command;
import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
     System.out.println("--- Standalone Undo/Redo Example (Text Editor with File I/O) ---");

     TextDocument myDocument = new TextDocument();
     TextEditor editor = new TextEditor(myDocument);

     Scanner scanner = new Scanner(System.in);
     String input;

     System.out.println("\nAvailable commands:");
     System.out.println("  'append <text>' : Appends text to the document.");
     System.out.println("  'delete <num>'  : Deletes the last 'num' characters.");
     System.out.println("  'save <filename>' : Saves current document content to a file."); // NEW
     System.out.println("  'load <filename>' : Loads document content from a file (clears history)."); // NEW
     System.out.println("  'undo'          : Undoes the last action.");
     System.out.println("  'redo'          : Redoes the last undone action.");
     System.out.println("  'content'       : Shows current document content.");
     System.out.println("  'quit'          : Exit the program.");

     while (true) {
         System.out.print("\nEnter command: ");
         input = scanner.nextLine().trim();

         if (input.equalsIgnoreCase("quit")) {
             System.out.println("Exiting editor. Goodbye!");
             break;
         } else if (input.equalsIgnoreCase("content")) {
             System.out.println("Current Document Content: \"" + editor.getCurrentDocumentContent() + "\"");
         } else if (input.startsWith("append ")) {
             String textToAppend = input.substring("append ".length()).trim();
             if (!textToAppend.isEmpty()) {
                 Command appendCommand = new AppendTextCommand(myDocument, textToAppend);
                 editor.performAction(appendCommand);
             } else {
                 System.out.println("Please provide text to append.");
             }
         } else if (input.startsWith("delete ")) {
             try {
                 int charsToDelete = Integer.parseInt(input.substring("delete ".length()).trim());
                 if (charsToDelete > 0) {
                     Command deleteCommand = new DeleteTextCommand(myDocument, charsToDelete);
                     editor.performAction(deleteCommand);
                 } else {
                     System.out.println("Number of characters to delete must be positive.");
                 }
             } catch (NumberFormatException e) {
                 System.out.println("Invalid number for delete. Usage: delete <num>");
             }
         }
         // --- NEW: Save Command ---
         else if (input.startsWith("save ")) {
             String filename = input.substring("save ".length()).trim();
             if (!filename.isEmpty()) {
                 myDocument.saveToFile(filename);
             } else {
                 System.out.println("Please provide a filename to save.");
             }
         }
         // --- NEW: Load Command ---
         else if (input.startsWith("load ")) {
             String filename = input.substring("load ".length()).trim();
             if (!filename.isEmpty()) {
                 if (myDocument.loadFromFile(filename)) {
                     editor.clearHistory(); // Clear undo/redo history after successful load
                     System.out.println("Main: Undo/Redo history cleared due to document load.");
                 }
             } else {
                 System.out.println("Please provide a filename to load.");
             }
         }
         // --- Existing Undo/Redo Commands ---
         else if (input.equalsIgnoreCase("undo")) {
             editor.undo();
         } else if (input.equalsIgnoreCase("redo")) {
             editor.redo();
         } else {
             System.out.println("Unknown command. Please try again.");
         }
     }
     scanner.close();
 }
}