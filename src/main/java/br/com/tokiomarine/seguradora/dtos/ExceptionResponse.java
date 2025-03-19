package br.com.tokiomarine.seguradora.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Respostas de erro")
public class ExceptionResponse {

    @ApiModelProperty(value = "Código de status do erro", example = "404")
    private String statusCode;

    @ApiModelProperty(value = "Mensagem de erro", example = "Cliente não encontrado")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ExceptionResponse(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ExceptionResponse() {
    }
}
