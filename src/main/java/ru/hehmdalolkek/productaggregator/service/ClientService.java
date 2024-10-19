package ru.hehmdalolkek.productaggregator.service;

import ru.hehmdalolkek.productaggregator.model.Client;

import java.util.List;

/**
 * Service interface for <code>Client</code> domain objects.
 *
 * @author Inna Badekha
 */
public interface ClientService {

    /**
     * Save <code>Client</code> in data store.
     *
     * @param client <code>Client</code> for creation
     * @return created <code>Client</code>
     */
    Client createClient(Client client);

    /**
     * Retrieve all <code>Client</code>s from the data store.
     *
     * @return list of <code>Client</code>
     */
    List<Client> getAllClients();

    /**
     * Retrieve the <code>Client</code> by id from the data store.
     *
     * @param clientId id to search for <code>Client</code>
     * @return founded <code>Client</code>
     */
    Client getClientById(Long clientId);

    /**
     * Delete <code>Client</code> by id from the data store.
     *
     * @param clientId id to delete for <code>Client</code>
     */
    void deleteClientById(Long clientId);

}
