package br.com.tokiomarine.seguradora.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<T> createResponse(T body, HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }
}