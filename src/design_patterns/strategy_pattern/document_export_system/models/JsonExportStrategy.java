package design_patterns.strategy_pattern.document_export_system.models;

import design_patterns.strategy_pattern.document_export_system.ExportStrategy;

public class JsonExportStrategy implements ExportStrategy{
	
    public JsonExportStrategy() { // Default constructor
    }

    @Override
    public void export(String documentContent) {
        System.out.println("Exporting document to Json format...");
        System.out.println("Content: " + documentContent); // Optionally print content
    }

    @Override
    public String getDocumentType() {
        return "JSON"; // Fixed string for this strategy
    }

}
