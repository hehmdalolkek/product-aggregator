package ru.hehmdalolkek.productaggregator.repository;

import org.springframework.stereotype.Repository;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.model.QProduct;

/**
 * Repository class for <code>Product</code> domain objects.
 *
 * @author Inna Badekha
 */
@Repository
public interface ProductRepository extends QueryDSLRepository<Product, QProduct, Long> {
}
