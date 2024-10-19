package ru.hehmdalolkek.productaggregator.util;

import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.model.Product;

import java.math.BigDecimal;

/**
 * Util class to get persistent and transient clients.
 *
 * @author Inna Badekha
 */
public class ProductUtil {

    public static Product getPersistedProduct1() {
        Client client = new Client();
        client.setId(1L);
        client.setUsername("Joe");
        client.setEmail("joe@email.com");
        Product product = new Product();
        product.setId(1L);
        product.setTitle("product1");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setClient(client);
        return product;
    }

    public static Product getPersistedProduct2() {
        Client client = new Client();
        client.setId(2L);
        client.setUsername("John");
        client.setEmail("john@email.com");
        Product product = new Product();
        product.setId(2L);
        product.setTitle("product2");
        product.setPrice(BigDecimal.valueOf(2000));
        product.setClient(client);
        return product;
    }

    public static Product getPersistedProduct3() {
        Client client = new Client();
        client.setId(3L);
        client.setUsername("Bob");
        client.setEmail("bob@email.com");
        Product product = new Product();
        product.setId(3L);
        product.setTitle("product3");
        product.setPrice(BigDecimal.valueOf(3000));
        product.setClient(client);
        return product;
    }

    public static Product getTransientProduct1() {
        Client client = new Client();
        client.setUsername("Joe");
        client.setEmail("joe@email.com");
        Product product = new Product();
        product.setTitle("product1");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setClient(client);
        return product;
    }

    public static Product getTransientProduct2() {
        Client client = new Client();
        client.setUsername("John");
        client.setEmail("john@email.com");
        Product product = new Product();
        product.setTitle("product2");
        product.setPrice(BigDecimal.valueOf(2000));
        product.setClient(client);
        return product;
    }

    public static Product getTransientProduct3() {
        Client client = new Client();
        client.setUsername("Bob");
        client.setEmail("bob@email.com");
        Product product = new Product();
        product.setTitle("product3");
        product.setPrice(BigDecimal.valueOf(3000));
        product.setClient(client);
        return product;
    }

}
