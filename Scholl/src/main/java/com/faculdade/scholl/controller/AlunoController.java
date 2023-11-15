package com.faculdade.scholl.controller;

import com.faculdade.scholl.model.Aluno;
import com.faculdade.scholl.service.impl.AlunoServiceImpl;
import com.faculdade.scholl.service.impl.RestImplAbstract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scholl/aluno")
public class AlunoController extends AbstractController<Aluno> {

    private final AlunoServiceImpl service;

    public AlunoController(AlunoServiceImpl service) {
        this.service = service;
    }

    @Override
    protected RestImplAbstract<Aluno> getService() {
        return this.service;
    }

}
