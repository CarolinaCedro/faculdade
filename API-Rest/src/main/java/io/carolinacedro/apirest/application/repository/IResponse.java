package io.carolinacedro.apirest.application.repository;

import io.carolinacedro.apirest.application.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponse extends JpaRepository<Response, Long> {
}
