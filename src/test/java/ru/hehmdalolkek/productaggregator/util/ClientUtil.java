package ru.hehmdalolkek.productaggregator.util;

import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.model.Product;

import java.math.BigDecimal;

/**
 * Util class to get persistent and transient clients.
 *
 * @author Inna Badekha
 */
public class ClientUtil {

    public static Client getPersistedClientJoe() {
        Client client = new Client();
        client.setId(1L);
        client.setUsername("Joe");
        client.setEmail("joe@email.com");
        return client;
    }

    public static Client getPersistedClientJohn() {
        Client client = new Client();
        client.setId(2L);
        client.setUsername("John");
        client.setEmail("john@email.com");
        return client;
    }

    public static Client getPersistedClientBob() {
        Client client = new Client();
        client.setId(3L);
        client.setUsername("Bob");
        client.setEmail("bob@email.com");
        return client;
    }

    public static Client getTransientClientJoe() {
        Client client = new Client();
        client.setUsername("Joe");
        client.setEmail("joe@email.com");
        return client;
    }

    public static Client getTransientClientJohn() {
        Client client = new Client();
        client.setUsername("John");
        client.setEmail("john@email.com");
        return client;
    }

    public static Client getTransientClientBob() {
        Client client = new Client();
        client.setUsername("Bob");
        client.setEmail("bob@email.com");
        return client;
    }

}
