package br.com.tokiomarine.seguradora.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Resposta com os detalhes de um endereço")
public class AddressResponse {
    @ApiModelProperty(value = "ID do endereço", example = "1")
    private Long id;

    @ApiModelProperty(value = "Logradouro do endereço", example = "Rua das Flores")
    private String address;

    @ApiModelProperty(value = "Número do endereço", example = "123")
    private String number;

    @ApiModelProperty(value = "Complemento do endereço", example = "Apto 101")
    private String complement;

    @ApiModelProperty(value = "CEP do endereço", example = "01001000")
    private String postalCode;

    @ApiModelProperty(value = "Cidade do endereço", example = "São Paulo")
    private String city;

    @ApiModelProperty(value = "Estado do endereço", example = "SP")
    private String state;

    @ApiModelProperty(value = "País do endereço", example = "Brasil")
    private String country;

    @ApiModelProperty(value = "Cliente")
    private ClientResponse client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ClientResponse getClient() {
        return client;
    }

    public void setClient(ClientResponse client) {
        this.client = client;
    }


}
