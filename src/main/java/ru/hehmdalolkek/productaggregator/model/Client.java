package ru.hehmdalolkek.productaggregator.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Simple JavaBean domain object representing a client.
 *
 * @author Inna Badekha
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client extends AbstractEntity {

    private String username;

    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client client)) {
            return false;
        }
        return Objects.equals(this.username, client.username)
                && Objects.equals(this.email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.email);
    }

}
