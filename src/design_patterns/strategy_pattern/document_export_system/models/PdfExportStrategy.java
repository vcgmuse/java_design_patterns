package design_patterns.strategy_pattern.document_export_system.models;

import design_patterns.strategy_pattern.document_export_system.ExportStrategy;

public class PdfExportStrategy implements ExportStrategy {
    // No 'type' field needed

    public PdfExportStrategy() { // Default constructor
    }

    @Override
    public void export(String documentContent) {
        System.out.println("Exporting document to PDF format...");
        System.out.println("Content: " + documentContent); // Optionally print content
    }

    @Override
    public String getDocumentType() {
        return "PDF"; // Fixed string for this strategy
    }
}
