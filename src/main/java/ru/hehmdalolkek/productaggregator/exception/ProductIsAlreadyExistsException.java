package ru.hehmdalolkek.productaggregator.exception;

/**
 * Custom exception for <code>Product</code>.
 * Means that the client is already exists.
 *
 * @author Inna Badekha
 */
public class ProductIsAlreadyExistsException extends RuntimeException {
    public ProductIsAlreadyExistsException(String message) {
        super(message);
    }
    public ProductIsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
