package io.carolinacedro.apirest.application.repository;

import io.carolinacedro.apirest.application.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequest extends JpaRepository<Request, Long> {
}
