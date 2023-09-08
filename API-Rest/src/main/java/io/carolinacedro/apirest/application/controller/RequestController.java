package io.carolinacedro.apirest.application.controller;

import io.carolinacedro.apirest.application.domain.Request;
import io.carolinacedro.apirest.application.service.AbstractService;
import io.carolinacedro.apirest.application.service.RequestServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController extends AbstractController<Request, Object> {

    private final RequestServiceImpl service;

    @Override
    protected AbstractService<Request> getService() {
        return this.service;
    }

    @Override
    protected Request convertToT(Object body) {
        return null;
    }


    public RequestController(RequestServiceImpl service) {
        this.service = service;
    }


}
