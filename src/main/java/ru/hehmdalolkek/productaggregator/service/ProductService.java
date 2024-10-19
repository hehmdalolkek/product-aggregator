package ru.hehmdalolkek.productaggregator.service;

import ru.hehmdalolkek.productaggregator.model.Product;

import java.util.List;

/**
 * Service interface for <code>Product</code> domain objects.
 *
 * @author Inna Badekha
 */
public interface ProductService {

    /**
     * Save <code>Product</code> in data store.
     *
     * @param product <code>Product</code> for creation
     * @return created <code>Product</code>
     */
    Product createProduct(Product product);

    /**
     * Retrieve all <code>Product</code>s of client from the data store.
     *
     * @param clientId id of <code>Client</code> for searching
     * @return list of <code>Product</code>
     */
    List<Product> getAllProductsByClientId(Long clientId);

    /**
     * Retrieve the <code>Product</code> by id from the data store.
     *
     * @param productId id to search for <code>Product</code>
     * @return founded <code>Product</code>
     */
    Product getProductById(Long productId);

    /**
     * Delete <code>Product</code> by id from the data store.
     *
     * @param productId id to delete for <code>Product</code>
     */
    void deleteProductById(Long productId);

}
