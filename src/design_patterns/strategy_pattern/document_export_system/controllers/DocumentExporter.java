package design_patterns.strategy_pattern.document_export_system.controllers;

import design_patterns.strategy_pattern.document_export_system.ExportStrategy;

public class DocumentExporter {
	public ExportStrategy exportStrategy;

	public void setExportStrategy(ExportStrategy exportStrategy) {
		this.exportStrategy = exportStrategy;
	}
	
	public void performExport(String documentContent) {
		exportStrategy.export(documentContent);	
	}

	public String getExportStrategy() {
		// TODO Auto-generated method stub
		return exportStrategy.getDocumentType();
	}
	
	

}
