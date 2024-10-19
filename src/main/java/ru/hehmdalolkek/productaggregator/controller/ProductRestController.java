package ru.hehmdalolkek.productaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.productaggregator.model.Product;
import ru.hehmdalolkek.productaggregator.service.ProductService;

import java.util.List;

/**
 * Rest controller for <code>Product</code>.
 *
 * @author Inna Badekha
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping("/client/{clientId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Product> getAllProductsByClientId(@PathVariable("clientId") Long clientId) {
        return this.productService.getAllProductsByClientId(clientId);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Product getProductById(@PathVariable("productId") Long productId) {
        return this.productService.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteProductById(@PathVariable("productId") Long productId) {
        this.productService.deleteProductById(productId);
    }

}
