package ru.hehmdalolkek.productaggregator.service;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hehmdalolkek.productaggregator.exception.ClientNotFoundException;
import ru.hehmdalolkek.productaggregator.exception.ProductIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ProductNotFoundException;
import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.repository.ClientRepository;
import ru.hehmdalolkek.productaggregator.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.hehmdalolkek.productaggregator.util.ClientUtil.getPersistedClientJoe;
import static ru.hehmdalolkek.productaggregator.util.ClientUtil.getPersistedClientJohn;
import static ru.hehmdalolkek.productaggregator.util.ProductUtil.getPersistedProduct1;
import static ru.hehmdalolkek.productaggregator.util.ProductUtil.getTransientProduct1;

/**
 * Unit test for {@link ProductServiceImpl}.
 *
 * @author Inna Badekha
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Test createProduct() success functionality")
    void givenProduct_whenCreateProduct_thenSuccess() {
        // given
        Product transientProduct = getTransientProduct1();
        Product persistedProduct = getPersistedProduct1();
        when(this.productRepository.save(any(Product.class)))
                .thenReturn(persistedProduct);

        // when
        Product result = this.productService.createProduct(transientProduct);

        // then
        assertThat(result.getId()).isEqualTo(persistedProduct.getId());
        assertThat(result.getTitle()).isEqualTo(persistedProduct.getTitle());
        assertThat(result.getPrice()).isEqualTo(persistedProduct.getPrice());
        verify(this.productRepository, times(1))
                .save(any(Product.class));
        verifyNoMoreInteractions(this.productRepository);
    }

    @Test
    @DisplayName("Test createProduct() unsuccessful functionality")
    void givenExistsProduct_whenCreateProduct_thenThrowException() {
        // given
        Product persistedProduct = getPersistedProduct1();
        when(this.productRepository.exists(any(Predicate.class)))
                .thenReturn(true);

        // when
        // then
        assertThatThrownBy(() -> this.productService.createProduct(persistedProduct))
                .isInstanceOf(ProductIsAlreadyExistsException.class)
                .hasMessage("Product with id=%d is already exists.".formatted(persistedProduct.getId()));
        verify(this.productRepository, times(1))
                .exists(any(Predicate.class));
        verifyNoMoreInteractions(this.productRepository);
    }

    @Test
    @DisplayName("Test getAllProductsByClientId() success functionality")
    void givenExistsClientId_whenGetAllProductsByClientId_thenReturnsProducts() {
        // given
        Client client = getPersistedClientJoe();
        Product product1 = new Product();
        product1.setTitle("product1");
        product1.setPrice(BigDecimal.valueOf(1000));
        product1.setClient(client);
        Product product2 = new Product();
        product2.setTitle("product2");
        product2.setPrice(BigDecimal.valueOf(2000));
        product2.setClient(client);
        List<Product> products = List.of(product1, product2);
        when(this.productRepository.findAll(any(Predicate.class)))
                .thenReturn(products);
        when(this.clientRepository.exists(any(Predicate.class)))
                .thenReturn(true);

        // when
        List<Product> result = this.productService.getAllProductsByClientId(client.getId());

        // then
        assertThat(result).isEqualTo(products);
        verify(this.productRepository, times(1))
                .findAll(any(Predicate.class));
        verify(this.clientRepository, times(1))
                .exists(any(Predicate.class));
        verifyNoMoreInteractions(this.productRepository, this.clientRepository);
    }

    @Test
    @DisplayName("Test getAllProductsByClientId() unsuccessful functionality")
    void givenNonExistsClientId_whenGetAllProductsByClientId_thenThrowException() {
        // given
        Long clientId = -1L;
        when(this.clientRepository.exists(any(Predicate.class)))
                .thenReturn(false);

        // when
        // then
        assertThatThrownBy(() -> this.productService.getAllProductsByClientId(clientId))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client with id=%d not found.".formatted(clientId));
        verify(this.clientRepository, times(1))
                .exists(any(Predicate.class));
        verifyNoMoreInteractions(this.productRepository, this.clientRepository);
    }

    @Test
    @DisplayName("Test getProductById() success functionality")
    void givenProductId_whenGetProductById_thenReturnProducts() {
        // given
        Product persistedProduct = getPersistedProduct1();
        Long clientId = persistedProduct.getId();
        when(this.productRepository.findOne(any(Predicate.class)))
                .thenReturn(Optional.of(persistedProduct));

        // when
        Product result = this.productService.getProductById(clientId);

        // then
        assertThat(result.getId()).isEqualTo(persistedProduct.getId());
        assertThat(result.getTitle()).isEqualTo(persistedProduct.getTitle());
        assertThat(result.getPrice()).isEqualTo(persistedProduct.getPrice());
        verify(this.productRepository, times(1))
                .findOne(any(Predicate.class));
        verifyNoMoreInteractions(this.productRepository);
    }

    @Test
    @DisplayName("Test getProductById() unsuccessful functionality")
    void givenNonExistsProductId_whenGetProductById_thenThrowException() {
        // given
        Long clientId = -1L;
        when(this.productRepository.findOne(any(Predicate.class)))
                .thenReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> this.productService.getProductById(clientId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product with id=%d not found.".formatted(clientId));
        verify(this.productRepository, times(1))
                .findOne(any(Predicate.class));
        verifyNoMoreInteractions(this.productRepository);
    }

    @Test
    @DisplayName("Test deleteProductById() functionality")
    void givenProductId_whenDeleteProductById_thenSuccess() {
        // given
        Product persistedProduct = getPersistedProduct1();
        Long clientId = persistedProduct.getId();

        // when
        this.productService.deleteProductById(clientId);

        // then
        verify(this.productRepository, times(1))
                .deleteById(anyLong());
        verifyNoMoreInteractions(this.productRepository);
    }

}