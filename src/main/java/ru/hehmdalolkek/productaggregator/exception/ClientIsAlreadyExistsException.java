package ru.hehmdalolkek.productaggregator.exception;

/**
 * Custom exception for <code>Client</code>.
 * Means that the client is already exists.
 *
 * @author Inna Badekha
 */
public class ClientIsAlreadyExistsException extends RuntimeException {
    public ClientIsAlreadyExistsException(String message) {
        super(message);
    }
    public ClientIsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
