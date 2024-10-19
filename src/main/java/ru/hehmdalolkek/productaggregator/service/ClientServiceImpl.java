package ru.hehmdalolkek.productaggregator.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.hehmdalolkek.productaggregator.exception.ClientIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ClientNotFoundException;
import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.model.QClient;
import ru.hehmdalolkek.productaggregator.repository.ClientRepository;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link ClientService} interface for managing clients.
 *
 * @author Inna Badekha
 */
@Service
@RequiredArgsConstructor
@Validated
public class ClientServiceImpl implements ClientService {

    /**
     * Repository for managing <code>Client</code> entities.
     */
    private final ClientRepository clientRepository;

    /**
     * Save <code>Client</code> in data store.
     * If <code>Client</code> has an id and given id is already exists,
     * throws {@link ClientIsAlreadyExistsException}.
     *
     * @param client <code>Client</code> for creation
     * @return created <code>Client</code>
     * @throws ClientIsAlreadyExistsException if the client has an id and such id already exists in the database
     */
    @Override
    @Transactional
    public Client createClient(Client client) {
        if (Objects.nonNull(client.getId())
                && this.clientRepository.exists(QClient.client.id.eq(client.getId()))) {
            throw new ClientIsAlreadyExistsException(
                    "Client with id=%d is already exists.".formatted(client.getId()));
        }
        return this.clientRepository.save(client);
    }

    /**
     * Retrieve all <code>Client</code>s from the data store.
     * Will return an empty list if no client is found.
     *
     * @return list of <code>Client</code>s
     */
    @Override
    @Transactional
    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    /**
     * Retrieves the <code>Client</code> by id from the data store.
     * If the client is not found, throws {@link ClientNotFoundException}.
     *
     * @param clientId id to search for <code>Client</code>
     * @return found <code>Client</code>
     * @throws ClientNotFoundException if the client with the given id does not exist
     */
    @Override
    @Transactional
    public Client getClientById(Long clientId) {
        return this.clientRepository.findOne(QClient.client.id.eq(clientId))
                .orElseThrow(() -> new ClientNotFoundException(
                        "Client with id=%d not found.".formatted(clientId)));
    }

    /**
     * Delete <code>Client</code> by id from the data store.
     *
     * @param clientId id to delete for <code>Client</code>
     */
    @Override
    @Transactional
    public void deleteClientById(Long clientId) {
        this.clientRepository.deleteById(clientId);
    }
}
