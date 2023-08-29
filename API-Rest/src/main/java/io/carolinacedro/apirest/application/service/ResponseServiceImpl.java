package io.carolinacedro.apirest.application.service;

import io.carolinacedro.apirest.application.domain.Request;
import io.carolinacedro.apirest.application.domain.Response;
import io.carolinacedro.apirest.application.domain.dto.ResponseDto;
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


    public Response create(ResponseDto body) {
        Request request = repository.findById(body.getRequest()).orElseThrow().getRequest();
        Response response = new Response(null, body.getResponse(), request);
        return this.repository.save(response);
    }
}
