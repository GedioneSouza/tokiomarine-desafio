package br.com.tokiomarine.seguradora.services;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.domain.Client;
import br.com.tokiomarine.seguradora.dtos.AddressRequest;
import br.com.tokiomarine.seguradora.infra.exceptions.AddressNotFoundException;
import br.com.tokiomarine.seguradora.infra.exceptions.ClientNotFoundException;
import br.com.tokiomarine.seguradora.repositories.AddressRepository;
import br.com.tokiomarine.seguradora.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("O endereço buscado não existe"));
    }

    public Address save(AddressRequest addressRequest) {
        Client client = clientRepository.findById(addressRequest.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Usuário não encontrado"));

        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setNumber(addressRequest.getNumber());
        address.setComplement(addressRequest.getComplement());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setClient(client);

        return addressRepository.save(address);
    }

    public Address update(Long id, AddressRequest addressRequest) {
        Optional<Address> existingAddress = Optional.ofNullable(addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("O endereço buscado não existe")));

        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();

            if (addressRequest.getClientId() != null) {
                Optional<Client> client = clientService.findById(addressRequest.getClientId());
                if (client.isPresent()) {
                    address.setClient(client.get());
                } else {
                    throw new RuntimeException("Cliente não encontrado com o ID: " + addressRequest.getClientId());
                }
            }

            address.setAddress(addressRequest.getAddress());
            address.setNumber(addressRequest.getNumber());
            address.setComplement(addressRequest.getComplement());
            address.setPostalCode(addressRequest.getPostalCode());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setCountry(addressRequest.getCountry());

            return addressRepository.save(address);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }

    public void deleteById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("O endereço buscado não existe"));
        addressRepository.delete(address);
    }
}
