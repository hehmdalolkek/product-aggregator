package ru.hehmdalolkek.productaggregator.repository;

import org.springframework.stereotype.Repository;
import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.model.QClient;

/**
 * Repository class for <code>Client</code> domain objects.
 *
 * @author Inna Badekha
 */
@Repository
public interface ClientRepository extends QueryDSLRepository<Client, QClient, Long> {
}
