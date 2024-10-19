package ru.hehmdalolkek.productaggregator.model;

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
 * Simple JavaBean domain object representing the aggregated products by title.
 *
 * @author Inna Badekha
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Products {

    @NotBlank
    @Size(min = 3)
    private String title;

    @NotNull
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
