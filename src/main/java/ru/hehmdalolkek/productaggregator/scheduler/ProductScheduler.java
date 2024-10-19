package ru.hehmdalolkek.productaggregator.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.model.Products;
import ru.hehmdalolkek.productaggregator.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Scheduler for sending products to kafka.
 *
 * @author Inna Badekha
 */
@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class ProductScheduler {

    /**
     * Kafka template for sending messages.
     */
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Product service for manipulation products.
     */
    private final ProductService productService;

    /**
     * Value of frequency of sending products.
     */
    @Value("${scheduler.products.send.rate.seconds}")
    private long productsSendIntervalInSeconds;

    /**
     * Name of kafka topic form product creation.
     */
    @Value("${broker.kafka.topics.product-creation}")
    private String productsCreationTopicName;

    /**
     * Periodically sends newly created products to Kafka, grouped by title.
     */
    @Scheduled(fixedRateString = "${scheduler.products.send.rate.seconds}", timeUnit = TimeUnit.SECONDS)
    public void sendProductsToKafka() {
        List<Product> products =
                this.productService.getAllProductsCreatedAtLastSeconds(this.productsSendIntervalInSeconds);
        List<Products> groupedProducts = groupProductsByTitleInProductsList(products);
        groupedProducts.forEach((productsData) -> {
            this.kafkaTemplate.send(this.productsCreationTopicName, productsData.getTitle(), productsData);
            log.debug("Products with title={} have been sent to Kafka {} topic", productsData.getTitle(), this.productsCreationTopicName);
        });
    }

    /**
     * Groups list of <code>Product</code> into list of <code>Products</code> by title and sum they price.
     *
     * @param products list of <code>Products</code>
     * @return list of <code>Products</Products></code> of grouped <code>Product</code>s
     */
    private List<Products> groupProductsByTitleInProductsList(List<Product> products) {
        List<Products> groupedProducts = new ArrayList<>();
        Map<String, BigDecimal> productsGroupByName = groupProductsByTitleIntoMap(products);
        productsGroupByName.forEach((title, cost) -> {
            Products productsData = new Products();
            productsData.setTitle(title);
            productsData.setTotalCost(cost);
            groupedProducts.add(productsData);
        });
        return groupedProducts;
    }

    /**
     * Groups list of <code>Product</code> into map by title and sum they price.
     *
     * @param products list of <code>Products</code>
     * @return <code>HashMap</code> of grouped <code>Product</code>s
     */
    private Map<String, BigDecimal> groupProductsByTitleIntoMap(List<Product> products) {
        return products.stream()
                .collect(
                        HashMap::new,
                        (map, product) -> {
                            String title = product.getTitle();
                            map.put(title, map.getOrDefault(title, BigDecimal.ZERO).add(product.getPrice()));
                        },
                        HashMap::putAll
                );
    }

}
