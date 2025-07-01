package design_patterns.decorator_pattern.file_access_control.interfaces;

// This abstract class implements the FileProcessor interface
// and provides a default delegation mechanism to the wrapped FileProcessor.
public abstract class FileProcessorDecorator implements FileProcessor {
    // This is the core: a reference to the FileProcessor object being decorated.
    protected FileProcessor decoratedProcessor;

    public FileProcessorDecorator(FileProcessor decoratedProcessor) {
        // Ensure that we always have a valid processor to wrap.
        if (decoratedProcessor == null) {
            throw new IllegalArgumentException("FileProcessor to decorate cannot be null.");
        }
        this.decoratedProcessor = decoratedProcessor;
    }

    @Override
    public byte[] readData() {
        // By default, decorators pass the readData request to the wrapped processor.
        // Concrete decorators will add logic *before* or *after* this call.
        return this.decoratedProcessor.readData();
    }

    @Override
    public boolean writeData(byte[] data) {
        // By default, decorators pass the writeData request to the wrapped processor.
        // Concrete decorators will add logic *before* or *after* this call.
        return this.decoratedProcessor.writeData(data);
    }

    @Override
    public String getDescription() {
        // By default, decorators include the description of the wrapped processor.
        // Concrete decorators will append their own specific description.
        return this.decoratedProcessor.getDescription();
    }
}