package io.carolinacedro.apirest.application.controller;

import io.carolinacedro.apirest.application.domain.Response;
import io.carolinacedro.apirest.application.domain.dto.ResponseDto;
import io.carolinacedro.apirest.application.service.AbstractService;
import io.carolinacedro.apirest.application.service.ResponseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/response")
public class ResponseController extends AbstractController<Response> {

    private final ResponseServiceImpl service;

    public ResponseController(ResponseServiceImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Response> getService() {
        return this.service;
    }

    @PostMapping("/respostas")
    public ResponseEntity<Response> createResponse(ResponseDto body) {
        return ResponseEntity.ok(service.create(body));
    }
}
