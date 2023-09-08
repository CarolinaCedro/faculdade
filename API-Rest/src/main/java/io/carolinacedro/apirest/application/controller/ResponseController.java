package io.carolinacedro.apirest.application.controller;

import io.carolinacedro.apirest.application.domain.Request;
import io.carolinacedro.apirest.application.domain.Response;
import io.carolinacedro.apirest.application.domain.dto.ResponseDto;
import io.carolinacedro.apirest.application.repository.IRequest;
import io.carolinacedro.apirest.application.service.AbstractService;
import io.carolinacedro.apirest.application.service.ResponseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController extends AbstractController<Response, ResponseDto> {

    private final ResponseServiceImpl service;
    private final IRequest repositoryRequest;

    public ResponseController(ResponseServiceImpl service, IRequest repositoryRequest) {
        this.service = service;
        this.repositoryRequest = repositoryRequest;
    }

    @Override
    protected AbstractService<Response> getService() {
        return this.service;
    }

    @Override
    protected Response convertToT(ResponseDto body) {
        Request request = repositoryRequest.findById(body.getRequest()).get();
        Response response = new Response(null, body.getResponse(), request.getPergunta(), request);
        return response;
    }

}
