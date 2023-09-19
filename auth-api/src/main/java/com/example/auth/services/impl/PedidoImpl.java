package com.example.auth.services.impl;

import com.example.auth.domain.pedido.Pedido;
import com.example.auth.repositories.PedidoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoImpl extends AbstractClassImpl<Pedido> {

    private final PedidoRepository repository;

    public PedidoImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Pedido, String> getRepository() {
        return this.repository;
    }
}
