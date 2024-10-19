package ru.hehmdalolkek.productaggregator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Simple JavaBean domain object representing the aggregated products by title.
 *
 * @author Inna Badekha
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Products {

    private String title;

    private BigDecimal totalCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Products products)) {
            return false;
        }
        return Objects.equals(this.title, products.title)
                && Objects.equals(this.totalCost, products.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.totalCost);
    }
}
