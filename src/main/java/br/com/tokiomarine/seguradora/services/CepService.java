package br.com.tokiomarine.seguradora.services;

import br.com.tokiomarine.seguradora.domain.Address;
import br.com.tokiomarine.seguradora.dtos.CepResponse;
import br.com.tokiomarine.seguradora.dtos.CepResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private static final String CEP_API_URL = "https://api.brasilaberto.com/v1/zipcode/";

    @Autowired
    private RestTemplate restTemplate;

    public Address getAddressByCep(String cep) throws Exception {
        String url = CEP_API_URL + cep;

        try {
            ResponseEntity<CepResponse> response = restTemplate.getForEntity(url, CepResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                CepResult result = response.getBody().getResult();

                if (result.isError()) {
                    throw new Exception("Erro na API: " + result.getMessage());
                }

                Address address = new Address();
                address.setPostalCode(result.getZipcode());
                address.setAddress(result.getStreet());
                address.setCity(result.getCity());
                address.setState(result.getState());
                address.setComplement(result.getComplement());
                return address;
            } else {
                throw new Exception("Erro ao buscar CEP: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new Exception("Acesso não autorizado à API. Verifique as credenciais.");
            } else {
                throw new Exception("Erro ao buscar CEP: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new Exception("Erro inesperado ao buscar CEP: " + e.getMessage());
        }
    }
}
