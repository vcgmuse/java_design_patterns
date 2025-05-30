package design_patterns.strategy_pattern.document_export_system;

public interface ExportStrategy {
	public void export(String documentType);
	public String getDocumentType();

}
