package br.com.tokiomarine.seguradora.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Resposta com os detalhes de um cliente")
public class ClientResponse {

    @ApiModelProperty(value = "ID do cliente", example = "1")
    private Long id;

    @ApiModelProperty(value = "Primeiro nome do cliente", example = "João")
    private String firstName;

    @ApiModelProperty(value = "Sobrenome do cliente", example = "Silva")
    private String lastName;

    @ApiModelProperty(value = "E-mail do cliente", example = "joao.silva@example.com")
    private String email;

    @ApiModelProperty(value = "Lista de endereços do cliente")
    private List<AddressResponse> addresses;

    public ClientResponse() {
    }

    public ClientResponse(Object o, String s) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
    }
}
