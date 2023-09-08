package io.carolinacedro.apirest.application.service;

import io.carolinacedro.apirest.application.domain.Response;
import io.carolinacedro.apirest.application.repository.IResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl extends AbstractServiceImpl<Response> {

    private final IResponse repository;

    public ResponseServiceImpl(IResponse repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Response, Long> getRepository() {
        return this.repository;
    }

}
