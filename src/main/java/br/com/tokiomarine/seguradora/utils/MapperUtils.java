package br.com.tokiomarine.seguradora.utils;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.domain.Client;
import br.com.tokiomarine.seguradora.dtos.AddressResponse;
import br.com.tokiomarine.seguradora.dtos.ClientResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    public static ClientResponse toClientResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setFirstName(client.getFirstName());
        response.setLastName(client.getLastName());
        response.setEmail(client.getEmail());

        if (client.getAddresses() != null) {
            List<AddressResponse> addresses = client.getAddresses().stream()
                    .map(MapperUtils::toAddressResponse)
                    .collect(Collectors.toList());
            response.setAddresses(addresses);
        }

        return response;
    }

    public static AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setAddress(address.getAddress());
        response.setNumber(address.getNumber());
        response.setComplement(address.getComplement());
        response.setPostalCode(address.getPostalCode());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCountry(address.getCountry());

        if (address.getClient() != null) {
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setId(address.getClient().getId());
            clientResponse.setFirstName(address.getClient().getFirstName());
            clientResponse.setLastName(address.getClient().getLastName());
            clientResponse.setEmail(address.getClient().getEmail());
            response.setClient(clientResponse);
        }

        return response;
    }
}
