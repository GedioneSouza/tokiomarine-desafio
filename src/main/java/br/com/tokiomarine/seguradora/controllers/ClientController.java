package br.com.tokiomarine.seguradora.controllers;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.domain.Client;
import br.com.tokiomarine.seguradora.dtos.AddressRequest;
import br.com.tokiomarine.seguradora.dtos.ClientRequest;
import br.com.tokiomarine.seguradora.dtos.ClientResponse;
import br.com.tokiomarine.seguradora.dtos.ExceptionResponse;
import br.com.tokiomarine.seguradora.infra.exceptions.ClientNotFoundException;
import br.com.tokiomarine.seguradora.services.ClientService;
import br.com.tokiomarine.seguradora.utils.MapperUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@Api(tags = "Clientes", description = "Operações relacionadas a clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ApiOperation(value = "Listar todos os clientes", notes = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes listados com sucesso")
    })
    public ResponseEntity<List<ClientResponse>> findAll() {
        List<Client> clients = clientService.findAll();
        List<ClientResponse> response = clients.stream()
                .map(MapperUtils::toClientResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar cliente por ID", notes = "Retorna os detalhes de um cliente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public ResponseEntity<?> findById(
            @ApiParam(value = "ID do cliente", example = "1", required = true)
            @PathVariable Long id) {
        try {
            Optional<Client> client = clientService.findById(id);
            ClientResponse response = MapperUtils.toClientResponse(client.get());
            return ResponseEntity.ok(response);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Criar um novo cliente", notes = "Cria um novo cliente com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado com sucesso"),
            @ApiResponse(code = 400, message = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ClientResponse> createUser(
            @ApiParam(value = "Dados do cliente", required = true)
            @RequestBody ClientRequest clientRequest
    ) {
        Client client = new Client();
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setEmail(clientRequest.getEmail());

        if (clientRequest.getAddresses() != null) {
            List<Address> addresses = clientRequest.getAddresses().stream()
                    .map(addressRequest -> {
                        Address address = new Address();
                        address.setAddress(addressRequest.getAddress());
                        address.setNumber(addressRequest.getNumber());
                        address.setComplement(addressRequest.getComplement());
                        address.setPostalCode(addressRequest.getPostalCode());
                        address.setCity(addressRequest.getCity());
                        address.setState(addressRequest.getState());
                        address.setCountry(addressRequest.getCountry());
                        address.setClient(client);
                        return address;
                    })
                    .collect(Collectors.toList());
            client.setAddresses(addresses);
        }

        Client savedClient = clientService.save(client);
        ClientResponse response = MapperUtils.toClientResponse(savedClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um cliente", notes = "Atualiza os dados de um cliente existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Dados inválidos fornecidos"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "ID do cliente", example = "1", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Dados atualizados do cliente", required = true)
            @RequestBody ClientRequest clientRequest) {

        if (clientRequest.getAddresses() != null) {
            for (AddressRequest addressRequest : clientRequest.getAddresses()) {
                if (addressRequest.getClientId() == null || !addressRequest.getClientId().equals(id)) {
                    return ResponseEntity.badRequest().body(new ExceptionResponse("400", "O clientId dos endereços deve ser igual ao id na URL."));
                }
            }
        }

        try {
            Optional<Client> client = clientService.findById(id);

            if (clientRequest.getFirstName() != null) {
                client.get().setFirstName(clientRequest.getFirstName());
            }
            if (clientRequest.getLastName() != null) {
                client.get().setLastName(clientRequest.getLastName());
            }
            if (clientRequest.getEmail() != null) {
                client.get().setEmail(clientRequest.getEmail());
            }

            if (clientRequest.getAddresses() != null) {
                List<Address> updatedAddresses = new ArrayList<>();

                for (AddressRequest addressRequest : clientRequest.getAddresses()) {
                    Optional<Address> existingAddress = client.get().getAddresses().stream()
                            .filter(a -> a.getId() != null && a.getId().equals(addressRequest.getId()))
                            .findFirst();

                    if (existingAddress.isPresent()) {
                        Address address = existingAddress.get();
                        address.setAddress(addressRequest.getAddress());
                        address.setNumber(addressRequest.getNumber());
                        address.setComplement(addressRequest.getComplement());
                        address.setPostalCode(addressRequest.getPostalCode());
                        address.setCity(addressRequest.getCity());
                        address.setState(addressRequest.getState());
                        address.setCountry(addressRequest.getCountry());
                        updatedAddresses.add(address);
                    } else {
                        Address newAddress = new Address();
                        newAddress.setAddress(addressRequest.getAddress());
                        newAddress.setNumber(addressRequest.getNumber());
                        newAddress.setComplement(addressRequest.getComplement());
                        newAddress.setPostalCode(addressRequest.getPostalCode());
                        newAddress.setCity(addressRequest.getCity());
                        newAddress.setState(addressRequest.getState());
                        newAddress.setCountry(addressRequest.getCountry());
                        newAddress.setClient(client.get());
                        updatedAddresses.add(newAddress);
                    }
                }

                client.get().getAddresses().clear();
                client.get().getAddresses().addAll(updatedAddresses);
            }

            Client updatedClient = clientService.save(client.get());
            ClientResponse response = MapperUtils.toClientResponse(updatedClient);
            return ResponseEntity.ok(response);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir um cliente", notes = "Exclui um cliente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente excluído com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public ResponseEntity<?> deleteById(
            @ApiParam(value = "ID do cliente", example = "1", required = true)
            @PathVariable Long id
    ) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }
}