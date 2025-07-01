package design_patterns.strategy_pattern.document_export_system.models;

import design_patterns.strategy_pattern.document_export_system.ExportStrategy;

public class CsvExportStrategy implements ExportStrategy{

	 public CsvExportStrategy() { // Default constructor
	    }

	    @Override
	    public void export(String documentContent) {
	        System.out.println("Exporting document to Csv format...");
	        System.out.println("Content: " + documentContent); // Optionally print content
	    }

	    @Override
	    public String getDocumentType() {
	        return "CSV"; // Fixed string for this strategy
	    }
}
