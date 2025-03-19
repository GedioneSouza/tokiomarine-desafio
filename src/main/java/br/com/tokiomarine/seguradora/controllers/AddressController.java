package br.com.tokiomarine.seguradora.controllers;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.dtos.AddressRequest;
import br.com.tokiomarine.seguradora.dtos.AddressResponse;
import br.com.tokiomarine.seguradora.dtos.ExceptionResponse;
import br.com.tokiomarine.seguradora.infra.exceptions.AddressNotFoundException;
import br.com.tokiomarine.seguradora.services.AddressService;
import br.com.tokiomarine.seguradora.services.CepService;
import br.com.tokiomarine.seguradora.utils.MapperUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addresses")
@Api(tags = "Endereços", description = "Operações relacionadas a endereços")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CepService cepService;

    @GetMapping
    @ApiOperation(value = "Listar todos os endereços", notes = "Retorna uma lista de todos os endereços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereços listados com sucesso"),
    })
    public ResponseEntity<List<AddressResponse>> findAll() {
        List<Address> addresses = addressService.findAll();
        List<AddressResponse> response = addresses.stream()
                .map(MapperUtils::toAddressResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar endereço por ID", notes = "Retorna os detalhes de um endereço com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço encontrado"),
            @ApiResponse(code = 404, message = "Endereço não encontrado")
    })
    public ResponseEntity<?> findById(
            @ApiParam(value = "ID do endereço", example = "1", required = true)
            @PathVariable Long id
    ) {
        try {
            Address address = addressService.findById(id);
            AddressResponse response = MapperUtils.toAddressResponse(address);
            return ResponseEntity.ok(response);
        } catch (AddressNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Criar um novo endereço", notes = "Cria um novo endereço com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Endereço criado com sucesso"),
            @ApiResponse(code = 400, message = "Dados inválidos fornecidos")
    })
    public ResponseEntity<AddressResponse> createAddress(
            @ApiParam(value = "Dados do endereço", required = true)
            @RequestBody AddressRequest addressRequest) {
        Address savedAddress = addressService.save(addressRequest);
        AddressResponse response = MapperUtils.toAddressResponse(savedAddress);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um endereço", notes = "Atualiza os dados de um endereço existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Dados inválidos fornecidos"),
            @ApiResponse(code = 404, message = "Endereço não encontrado")
    })
    public ResponseEntity<?> updateAddress(
            @ApiParam(value = "ID do endereço", example = "1", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Dados atualizados do endereço", required = true)
            @RequestBody AddressRequest addressRequest) {
        try {
            Address updatedAddress = addressService.update(id, addressRequest);
            AddressResponse response = MapperUtils.toAddressResponse(updatedAddress);
            return ResponseEntity.ok(response);
        } catch (AddressNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir um endereço", notes = "Exclui um endereço com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Endereço excluído com sucesso"),
            @ApiResponse(code = 404, message = "Endereço não encontrado")
    })
    public ResponseEntity<?> deleteById(
            @ApiParam(value = "ID do endereço", example = "1", required = true)
            @PathVariable Long id
    ) {
        try {
            addressService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (AddressNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("404", ex.getMessage()));
        }
    }

    @GetMapping("/cep/{cep}")
    @ApiOperation(value = "Buscar endereço por CEP", notes = "Retorna os detalhes de um endereço com base no CEP fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço encontrado"),
            @ApiResponse(code = 404, message = "Endereço não encontrado")
    })
    public ResponseEntity<Address> getAddressByCep(
            @ApiParam(value = "CEP do endereço", example = "01001000", required = true)
            @PathVariable String cep) throws Exception {
        Address address = cepService.getAddressByCep(cep);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}