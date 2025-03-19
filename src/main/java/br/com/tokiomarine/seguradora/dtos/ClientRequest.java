package br.com.tokiomarine.seguradora.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Dados necessários para criar ou atualizar um cliente")
public class ClientRequest {

    @ApiModelProperty(value = "Primeiro nome do cliente", example = "João", required = true)
    private String firstName;

    @ApiModelProperty(value = "Sobrenome do cliente", example = "Silva", required = true)
    private String lastName;

    @ApiModelProperty(value = "E-mail do cliente", example = "joao.silva@example.com", required = true)
    private String email;

    @ApiModelProperty(value = "Lista de endereços do cliente")
    private List<AddressRequest> addresses;

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

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
}
