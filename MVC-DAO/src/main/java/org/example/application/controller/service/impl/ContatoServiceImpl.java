package org.example.application.controller.service.impl;


import org.example.application.config.ConnectionMySQLDAO;
import org.example.application.model.Contato;
import org.example.application.controller.service.ContatosService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatoServiceImpl implements ContatosService<Contato> {

    @Override
    public long create(Contato contato) {
        String query = "INSERT INTO contatos (nome, apelido, data_nascimento) VALUES (?,?,?)";
        ConnectionMySQLDAO.executeQuery(query, contato.getNome(), contato.getApelido(), contato.getData_nascimento());

        return 0;
    }

    @Override
    public void update(Long id, Contato contato) {
        //UPDATE contatos SET nome='fghjfghj', apelido='deu', data_nascimento='2023-09-23' WHERE id=7

        String query = "UPDATE contatos SET nome=?, apelido=?, data_nascimento=? WHERE id=?";
        ConnectionMySQLDAO.executeQuery(query, contato.getNome(), contato.getApelido(), contato.getData_nascimento(), id);
        System.out.println("O id passado é " + id);

        System.out.println("aqui o body" + contato);


    }


    @Override
    public void delete(Long id) {
        Contato contato = this.findContato(id);
        if (contato != null) {
            ConnectionMySQLDAO.executeQuery("DELETE FROM contatos WHERE id = ?", id);
        }
    }

    @Override
    public ArrayList<Contato> findAllPessoa() {
        ArrayList<Contato> lista = new ArrayList<Contato>();
        ResultSet rs = null;
        String query = "SELECT * FROM contatos;";
        rs = ConnectionMySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("apelido"),

                        rs.getDate("data_nascimento")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Contato findContato(Long id) {
        String query = "SELECT * FROM contatos WHERE id=?";

        try (ResultSet rs = ConnectionMySQLDAO.getResultSet(query, id)) {
            if (rs.next()) {
                return new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("apelido"), rs.getDate("data_nascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int findId(Contato contato) {
        String query = "SELECT * FROM contatos WHERE nome= ? and apelido= ? and data_nascimento = ?";

        try (ResultSet rs = ConnectionMySQLDAO.getResultSet(query, contato.getNome(), contato.getApelido(), contato.getData_nascimento())) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Boolean isExist(Long id) {
        String query = "SELECT * FROM contatos WHERE id= ?";

        try (ResultSet rs = ConnectionMySQLDAO.getResultSet(query, id)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

