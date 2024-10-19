package ru.hehmdalolkek.productaggregator.exception;

/**
 * Custom exception for <code>Product</code>.
 * Means that the object was not found.
 *
 * @author Inna Badekha
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
