package br.com.tokiomarine.seguradora.repositories;

import br.com.tokiomarine.seguradora.domain.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @EntityGraph(attributePaths = {"addresses"})
    List<Client> findAll();

    @EntityGraph(attributePaths = {"addresses"})
    Optional<Client> findById(Long id);
}
