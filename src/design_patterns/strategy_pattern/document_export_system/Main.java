package design_patterns.strategy_pattern.document_export_system;

import design_patterns.strategy_pattern.document_export_system.controllers.DocumentExporter;
import design_patterns.strategy_pattern.document_export_system.models.CsvExportStrategy;
import design_patterns.strategy_pattern.document_export_system.models.JsonExportStrategy;
import design_patterns.strategy_pattern.document_export_system.models.PdfExportStrategy;

public class Main {
    public static void main(String[] args) {
        DocumentExporter documentExporter = new DocumentExporter();
        String actualDocumentData = "CustomerName, Product, Quantity\nAlice, Laptop, 1\nBob, Mouse, 2"; // Actual data

        // Export as PDF
        documentExporter.setExportStrategy(new PdfExportStrategy()); // No need for 'type' in constructor
        System.out.println("Current Export Strategy: PDF"); // Better message
        documentExporter.performExport(actualDocumentData); // Pass actual data

        System.out.println("---");

        // Export as CSV
        documentExporter.setExportStrategy(new CsvExportStrategy());
        System.out.println("Current Export Strategy: CSV");
        documentExporter.performExport(actualDocumentData);

        System.out.println("---");

        // Export as JSON
        documentExporter.setExportStrategy(new JsonExportStrategy());
        System.out.println("Current Export Strategy: JSON");
        documentExporter.performExport(actualDocumentData);
    }
}
