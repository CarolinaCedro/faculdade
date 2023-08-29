package io.carolinacedro.apirest.application.service;

import io.carolinacedro.apirest.application.domain.Request;
import io.carolinacedro.apirest.application.domain.dto.ResponseDto;
import io.carolinacedro.apirest.application.repository.IRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl extends AbstractServiceImpl<Request> {

    private final IRequest repository;

    public RequestServiceImpl(IRequest repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Request, Long> getRepository() {
        return this.repository;
    }

}
