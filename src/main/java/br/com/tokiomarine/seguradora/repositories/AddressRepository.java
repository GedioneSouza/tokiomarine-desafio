package br.com.tokiomarine.seguradora.repositories;

import br.com.tokiomarine.seguradora.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}