package ru.hehmdalolkek.productaggregator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Entity
public class Product extends AbstractEntity {

    @NotBlank
    @Size(min = 3)
    private String title;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "client_id")
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
