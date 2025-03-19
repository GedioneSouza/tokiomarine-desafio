package br.com.tokiomarine.seguradora.services;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.domain.Client;
import br.com.tokiomarine.seguradora.infra.exceptions.ClientNotFoundException;
import br.com.tokiomarine.seguradora.repositories.AddressRepository;
import br.com.tokiomarine.seguradora.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Usuário não encontrado.")));
    }

    public Client save(Client client) {
        Client savedClient = clientRepository.save(client);

        if (client.getAddresses() != null) {
            for (Address address : client.getAddresses()) {
                address.setClient(savedClient);
                addressRepository.save(address);
            }
        }

        return savedClient;
    }

    public void deleteById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Usuário não encontrado."));
        clientRepository.delete(client);
    }
}