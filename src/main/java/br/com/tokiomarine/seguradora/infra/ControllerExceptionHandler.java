package br.com.tokiomarine.seguradora.infra;

import br.com.tokiomarine.seguradora.dtos.ExceptionResponse;
import br.com.tokiomarine.seguradora.infra.exceptions.AddressNotFoundException;
import br.com.tokiomarine.seguradora.utils.ResponseUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> threatGeneralException(Exception exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse(exception.getMessage(), "500");
        return ResponseUtil.createResponse(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ExceptionResponse> threatUnauthorizedException(HttpClientErrorException.Unauthorized exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse("Acesso não autorizado", "401");
        return ResponseUtil.createResponse(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ExceptionResponse> threatBadRequestException(HttpClientErrorException.BadRequest exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse("Requisição inválida", "400");
        return ResponseUtil.createResponse(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> threatBadRequestException(HttpClientErrorException.NotFound exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse("O endereço buscado não existe", "400");
        return ResponseUtil.createResponse(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception) {
        ExceptionResponse exceptionDTO = new ExceptionResponse(exception.getMessage(), "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
