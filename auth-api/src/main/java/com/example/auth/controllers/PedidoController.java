package com.example.auth.controllers;

import com.example.auth.domain.pedido.Pedido;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.PedidoImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("pedido")
public class PedidoController extends RestControllerImpl<Pedido> {

    private final PedidoImpl service;

    public PedidoController(PedidoImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Pedido> getService() {
        return this.service;
    }
}

