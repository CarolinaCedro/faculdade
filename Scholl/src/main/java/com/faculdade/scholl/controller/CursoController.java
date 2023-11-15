package com.faculdade.scholl.controller;

import com.faculdade.scholl.dto.CursoDtoRequest;
import com.faculdade.scholl.model.Curso;
import com.faculdade.scholl.service.impl.CursoServiceImpl;
import com.faculdade.scholl.service.impl.RestImplAbstract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/scholl/curso")
public class CursoController extends AbstractController<Curso> {

    private final CursoServiceImpl service;

    public CursoController(CursoServiceImpl service) {
        this.service = service;
    }

    @Override
    protected RestImplAbstract<Curso> getService() {
        return this.service;
    }


    @PostMapping(value = "/post")
    public ResponseEntity<Void> create(CursoDtoRequest body) {
        Curso curso = this.service.save(body).getBody();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(curso.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
