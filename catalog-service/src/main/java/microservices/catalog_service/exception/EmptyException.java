package microservices.catalog_service.exception;

public class EmptyException extends RuntimeException {
    public EmptyException(String message) {
        super(message);
    }
}
