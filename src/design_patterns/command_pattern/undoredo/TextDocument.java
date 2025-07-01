package design_patterns.command_pattern.undoredo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextDocument {
    private StringBuilder content;

    public TextDocument() {
        this.content = new StringBuilder();
        System.out.println("TextDocument: Created new document.");
    }

    public void append(String text) {
        content.append(text);
        System.out.println("TextDocument: Appended \"" + text + "\" | Current content: \"" + content.toString() + "\"");
    }

    // Deletes characters from the end of the document
    public String deleteLast(int count) {
        if (content.length() == 0 || count <= 0) {
            return ""; // Nothing to delete
        }
        int startIndex = Math.max(0, content.length() - count);
        String deletedText = content.substring(startIndex);
        content.delete(startIndex, content.length());
        System.out.println("TextDocument: Deleted \"" + deletedText + "\" | Current content: \"" + content.toString() + "\"");
        return deletedText;
    }

    public String getContent() {
        return content.toString();
    }

    // --- NEW: Save to File ---
    public boolean saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content.toString());
            System.out.println("TextDocument: Content saved to " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("TextDocument: Error saving to file " + filename + ": " + e.getMessage());
            return false;
        }
    }

    // --- NEW: Load from File ---
    public boolean loadFromFile(String filename) {
        StringBuilder loadedContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedContent.append(line).append(System.lineSeparator()); // Add line separator back
            }
            // Remove the last line separator if present, to match original content without trailing newline
            if (loadedContent.length() > 0 && System.lineSeparator().length() > 0) {
                 if (loadedContent.substring(loadedContent.length() - System.lineSeparator().length()).equals(System.lineSeparator())) {
                     loadedContent.setLength(loadedContent.length() - System.lineSeparator().length());
                 }
            }

            this.content.setLength(0); // Clear current content
            this.content.append(loadedContent); // Append loaded content
            System.out.println("TextDocument: Content loaded from " + filename);
            System.out.println("TextDocument: Current content is now: \"" + this.content.toString() + "\"");
            return true;
        } catch (IOException e) {
            System.err.println("TextDocument: Error loading from file " + filename + ": " + e.getMessage());
            this.content.setLength(0); // Clear content on load failure
            return false;
        }
    }
}