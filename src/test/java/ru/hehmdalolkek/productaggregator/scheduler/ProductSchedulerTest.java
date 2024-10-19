package ru.hehmdalolkek.productaggregator.scheduler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.model.Products;
import ru.hehmdalolkek.productaggregator.service.ProductService;
import ru.hehmdalolkek.productaggregator.util.ProductUtil;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link ProductScheduler}.
 *
 * @author Inna Badekha
 */
@ExtendWith(MockitoExtension.class)
class ProductSchedulerTest {

    @InjectMocks
    ProductScheduler productScheduler;

    @Mock
    ProductService productService;

    @Mock
    KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    @DisplayName("Test sendProductsToKafka() functionality")
    public void givenProducts_whenSendProductsToKafka_thenSuccess() {
        // given
        Product productA1 = ProductUtil.getPersistedProduct1();
        Product productA2 = ProductUtil.getTransientProduct1();
        Product productB3 = ProductUtil.getPersistedProduct2();
        List<Product> products = List.of(productA1, productA2, productB3);
        when(this.productService.getAllProductsCreatedAtLastSeconds(anyLong()))
                .thenReturn(products);

        // when
        this.productScheduler.sendProductsToKafka();

        // then
        ArgumentCaptor<Products> captor = ArgumentCaptor.forClass(Products.class);
        verify(this.kafkaTemplate, times(2))
                .send(any(), anyString(), captor.capture());
        assertThat(captor.getAllValues())
                .hasSize(2)
                .extracting(Products::getTitle, Products::getTotalCost)
                .containsExactlyInAnyOrder(
                        tuple("product1", BigDecimal.valueOf(2000)),
                        tuple("product2", BigDecimal.valueOf(2000))
                );
        verify(this.productService, times(1))
                .getAllProductsCreatedAtLastSeconds(anyLong());
        verifyNoMoreInteractions(this.productService, this.kafkaTemplate);
    }

}