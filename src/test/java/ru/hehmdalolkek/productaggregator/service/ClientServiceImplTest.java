package ru.hehmdalolkek.productaggregator.service;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hehmdalolkek.productaggregator.exception.ClientIsAlreadyExistsException;
import ru.hehmdalolkek.productaggregator.exception.ClientNotFoundException;
import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.hehmdalolkek.productaggregator.util.ClientUtil.*;

/**
 * Unit test for {@link ClientServiceImpl}.
 *
 * @author Inna Badekha
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Test
    @DisplayName("Test createClient() success functionality")
    void givenClient_whenCreateClient_thenSuccess() {
        // given
        Client transientClient = getTransientClientJoe();
        Client persistedClient = getPersistedClientJoe();
        when(this.clientRepository.save(any(Client.class)))
                .thenReturn(persistedClient);

        // when
        Client result = this.clientService.createClient(transientClient);

        // then
        assertThat(result.getId()).isEqualTo(persistedClient.getId());
        assertThat(result.getUsername()).isEqualTo(persistedClient.getUsername());
        assertThat(result.getEmail()).isEqualTo(persistedClient.getEmail());
        verify(this.clientRepository, times(1))
                .save(any(Client.class));
        verifyNoMoreInteractions(this.clientRepository);
    }

    @Test
    @DisplayName("Test createClient() unsuccessful functionality")
    void givenExistsClient_whenCreateClient_thenThrowException() {
        // given
        Client persistedClient = getPersistedClientJoe();
        when(this.clientRepository.exists(any(Predicate.class)))
                .thenReturn(true);

        // when
        // then
        assertThatThrownBy(() -> this.clientService.createClient(persistedClient))
                .isInstanceOf(ClientIsAlreadyExistsException.class)
                .hasMessage("Client with id=%d is already exists.".formatted(persistedClient.getId()));
        verify(this.clientRepository, times(1))
                .exists(any(Predicate.class));
        verifyNoMoreInteractions(this.clientRepository);
    }

    @Test
    @DisplayName("Test getAllClients() functionality")
    void whenGetAllClients_thenReturnsClients() {
        // given
        Client client1 = getPersistedClientJoe();
        Client client2 = getPersistedClientJohn();
        List<Client> clients = List.of(client1, client2);
        when(this.clientRepository.findAll())
                .thenReturn(clients);

        // when
        List<Client> result = this.clientService.getAllClients();

        // then
        assertThat(result).isEqualTo(clients);
        verify(this.clientRepository, times(1))
                .findAll();
        verifyNoMoreInteractions(this.clientRepository);
    }

    @Test
    @DisplayName("Test getClientById() success functionality")
    void givenClientId_whenGetClientById_thenReturnClients() {
        // given
        Client persistedClient = getPersistedClientJoe();
        Long clientId = persistedClient.getId();
        when(this.clientRepository.findOne(any(Predicate.class)))
                .thenReturn(Optional.of(persistedClient));

        // when
        Client result = this.clientService.getClientById(clientId);

        // then
        assertThat(result.getId()).isEqualTo(persistedClient.getId());
        assertThat(result.getUsername()).isEqualTo(persistedClient.getUsername());
        assertThat(result.getEmail()).isEqualTo(persistedClient.getEmail());
        verify(this.clientRepository, times(1))
                .findOne(any(Predicate.class));
        verifyNoMoreInteractions(this.clientRepository);
    }

    @Test
    @DisplayName("Test getClientById() unsuccessful functionality")
    void givenNonExistsClientId_whenGetClientById_thenThrowException() {
        // given
        Long clientId = -1L;
        when(this.clientRepository.findOne(any(Predicate.class)))
                .thenReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> this.clientService.getClientById(clientId))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("Client with id=%d not found.".formatted(clientId));
        verify(this.clientRepository, times(1))
                .findOne(any(Predicate.class));
        verifyNoMoreInteractions(this.clientRepository);
    }

    @Test
    @DisplayName("Test deleteClientById() functionality")
    void givenClientId_whenDeleteClientById_thenSuccess() {
        // given
        Client persistedClient = getPersistedClientJoe();
        Long clientId = persistedClient.getId();

        // when
        this.clientService.deleteClientById(clientId);

        // then
        verify(this.clientRepository, times(1))
                .deleteById(anyLong());
        verifyNoMoreInteractions(this.clientRepository);
    }

}