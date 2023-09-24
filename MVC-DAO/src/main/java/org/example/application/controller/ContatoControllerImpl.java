package org.example.application.controller;

import org.example.application.config.ConnectionMySQLDAO;
import org.example.application.model.Contato;
import org.example.application.controller.service.ContatosService;
import org.example.application.controller.service.impl.ContatoServiceImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ContatoControllerImpl implements ContatoController<Contato> {
    private ContatosService<Contato> contatosService;

    public ContatoControllerImpl() {
        this.contatosService = new ContatoServiceImpl(); // Crie a instância do serviço no construtor.
        Connection con = ConnectionMySQLDAO.getConnection();
    }

    @Override
    public List<Contato> getAllContatos() {
        return contatosService.findAllPessoa(); // Chame o método correspondente do serviço.
    }

    @Override
    public Optional<Contato> getOneContato(Long id) {
        Contato contato = contatosService.findContato(id); // Chame o método correspondente do serviço.
        return Optional.ofNullable(contato);
    }

    @Override
    public long createContato(Contato body) {
        return contatosService.create(body); // Chame o método correspondente do serviço.
    }

    @Override
    public void updateContato(Long id, Contato body) {
        System.out.println("aqui o ai no controller" + id);
        body.setId(id);
        contatosService.update(id,body); // Chame o método correspondente do serviço.
    }

    @Override
    public long deleteContato(Long id) {
        System.out.println("esse é o id " + id);
        if (contatosService.isExist(id)) {
            System.out.println("existe id");
            contatosService.delete(id); // Chame o método correspondente do serviço.
            return 1;
        } else {
            return 0;
        }
    }
}

