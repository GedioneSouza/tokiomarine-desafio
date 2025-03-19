package br.com.tokiomarine.seguradora.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Dados necessários para criar ou atualizar um endereço")
public class AddressRequest {
    @ApiModelProperty(value = "ID do endereço", example = "1")
    private String id;

    @ApiModelProperty(value = "Logradouro do endereço", example = "Rua das Flores", required = true)
    private String address;

    @ApiModelProperty(value = "Número do endereço", example = "123", required = true)
    private String number;

    @ApiModelProperty(value = "Complemento do endereço", example = "Apto 101")
    private String complement;

    @ApiModelProperty(value = "CEP do endereço", example = "01001000", required = true)
    private String postalCode;

    @ApiModelProperty(value = "Cidade do endereço", example = "São Paulo", required = true)
    private String city;

    @ApiModelProperty(value = "Estado do endereço", example = "SP", required = true)
    private String state;

    @ApiModelProperty(value = "País do endereço", example = "Brasil", required = true)
    private String country;

    @ApiModelProperty(value = "ID do cliente associado ao endereço", example = "1", required = true)
    private Long clientId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}