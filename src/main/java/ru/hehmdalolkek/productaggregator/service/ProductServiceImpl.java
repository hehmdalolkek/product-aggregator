package ru.hehmdalolkek.productaggregator.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.hehmdalolkek.productaggregator.exception.ClientNotFoundException;
import ru.hehmdalolkek.productaggregator.exception.ProductIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ProductNotFoundException;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.model.QClient;
import ru.hehmdalolkek.productaggregator.model.QProduct;
import ru.hehmdalolkek.productaggregator.repository.ClientRepository;
import ru.hehmdalolkek.productaggregator.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * Implementation of {@link ProductService} interface for managing clients.
 *
 * @author Inna Badekha
 */
@Service
@RequiredArgsConstructor
@Validated
public class ProductServiceImpl implements ProductService {

    /**
     * Repository for managing <code>Product</code> entities.
     */
    private final ProductRepository productRepository;

    /**
     * Repository for managing <code>Client</code> entities.
     */
    private final ClientRepository clientRepository;

    /**
     * Save <code>Product</code> in data store.
     * If <code>Product</code> has an id and given id is already exists,
     * throws {@link ProductIsAlreadyExistsException}.
     *
     * @param product <code>Product</code> for creation
     * @return created <code>Product</code>
     * @throws ProductIsAlreadyExistsException if the product has an id and such id already exists in the database
     */
    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (Objects.nonNull(product.getId())
                && this.productRepository.exists(QProduct.product.id.eq(product.getId()))) {
            throw new ProductIsAlreadyExistsException(
                    "Product with id=%d is already exists.".formatted(product.getId()));
        }
        return this.productRepository.save(product);
    }

    /**
     * Retrieves the <code>Product</code>s by client id from the data store.
     * Will return an empty list if no products is found.
     * Throws {@link ClientNotFoundException} if client is not exists;
     *
     * @param clientId id of <code>Client</code> for searching
     * @return list of <code>Product</code>s
     * @throws ClientNotFoundException if client is not exists
     */
    @Override
    @Transactional
    public List<Product> getAllProductsByClientId(Long clientId) {
        if (!this.clientRepository.exists(QClient.client.id.eq(clientId))) {
            throw new ClientNotFoundException("Client with id=%d not found.".formatted(clientId));
        }
        Iterable<Product> products =
                this.productRepository.findAll(QProduct.product.client.id.eq(clientId));
        return StreamSupport.stream(products.spliterator(), false)
                .toList();
    }

    /**
     * Retrieves the <code>Product</code>s by id from the data store.
     * If the product is not found, throws {@link ProductNotFoundException}.
     *
     * @param productId id to search for <code>Product</code>
     * @return found <code>Product</code>
     * @throws ProductNotFoundException if the product with the given id does not exist
     */
    @Override
    @Transactional
    public Product getProductById(Long productId) {
        return this.productRepository.findOne(QProduct.product.id.eq(productId))
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with id=%d not found.".formatted(productId)));
    }

    /**
     * Delete <code>Product</code> by id from the data store.
     *
     * @param productId id to delete for <code>Product</code>
     */
    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        this.productRepository.deleteById(productId);
    }
}
