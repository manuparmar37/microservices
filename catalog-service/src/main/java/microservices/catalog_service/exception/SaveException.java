package microservices.catalog_service.exception;

public class SaveException extends RuntimeException {
    public SaveException(String message) {
        super(message);
    }
}
