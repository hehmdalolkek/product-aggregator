package ru.hehmdalolkek.productaggregator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A simple JavaBean domain object representing the product.
 *
 * @author Inna Badekha
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product extends AbstractEntity {

    private String title;

    private BigDecimal price;

    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(this.title, product.title)
                && Objects.equals(this.price, product.price)
                && Objects.equals(this.client, product.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.price, this.client);
    }

}
