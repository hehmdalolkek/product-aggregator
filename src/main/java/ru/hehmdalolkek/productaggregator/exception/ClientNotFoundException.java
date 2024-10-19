package ru.hehmdalolkek.productaggregator.exception;

/**
 * Custom exception for <code>Client</code>.
 * Means that the object was not found.
 *
 * @author Inna Badekha
 */
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
