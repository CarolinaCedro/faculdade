package org.example.application.view;

import org.example.application.controller.ContatoController;
import org.example.application.controller.ContatoControllerImpl;
import org.example.application.model.Contato;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PrincipalMenu {
    private final ContatoController<Contato> contatoController;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PrincipalMenu(ContatoController<Contato> contatoController) {
        this.contatoController = contatoController;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void exibirMenu() {
        while (true) {
            String[] opcoes = {
                    "Listar todos os contatos",
                    "Buscar um contato por ID",
                    "Criar um novo contato",
                    "Atualizar um contato",
                    "Excluir um contato",
                    "Sair"
            };

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0:
                    listarContatos();
                    break;
                case 1:
                    buscarContatoPorId();
                    break;
                case 2:
                    criarNovoContato();
                    break;
                case 3:
                    atualizarContato();
                    break;
                case 4:
                    excluirContato();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    System.exit(0);
            }
        }
    }

    private void listarContatos() {
        try {
            List<Contato> contatos = contatoController.getAllContatos();
            StringBuilder listaText = new StringBuilder("=== Lista de Contatos ===\n");
            for (Contato contato : contatos) {
                listaText.append(contato).append("\n");
            }
            JOptionPane.showMessageDialog(null, listaText.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos: " + e.getMessage());
        }
    }

    private void buscarContatoPorId() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do contato:");
        try {
            Long id = Long.parseLong(idStr);
            Optional<Contato> optionalContato = contatoController.getOneContato(id);
            if (optionalContato.isPresent()) {
                JOptionPane.showMessageDialog(null, "=== Contato Encontrado ===\n" + optionalContato.get());
            } else {
                JOptionPane.showMessageDialog(null, "Contato não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar contato: " + e.getMessage());
        }
    }

    private void criarNovoContato() {
        String nome = JOptionPane.showInputDialog("Digite o nome do contato:");
        String apelido = JOptionPane.showInputDialog("Digite o apelido do contato:");
        String dataNascimentoStr = JOptionPane.showInputDialog("Digite a data de nascimento (AAAA-MM-DD):");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascimento = dateFormat.parse(dataNascimentoStr);

            Contato novoContato = new Contato(null, nome, apelido, dataNascimento);
            contatoController.createContato(novoContato);
            JOptionPane.showMessageDialog(null, "Contato criado com sucesso.");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato AAAA-MM-DD.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o contato: " + e.getMessage());
        }
    }

    private void atualizarContato() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do contato que deseja atualizar:");
        try {
            Long id = Long.parseLong(idStr);
            Optional<Contato> optionalContato = contatoController.getOneContato(id);
            if (optionalContato.isPresent()) {
                Contato contatoExistente = optionalContato.get();
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome (ou pressione Enter para manter o atual):", contatoExistente.getNome());
                if (!novoNome.isEmpty()) {
                    contatoExistente.setNome(novoNome);
                }

                String novoApelido = JOptionPane.showInputDialog("Digite o novo apelido (ou pressione Enter para manter o atual):", contatoExistente.getApelido());
                if (!novoApelido.isEmpty()) {
                    contatoExistente.setApelido(novoApelido);
                }

                String novaDataNascimentoStr = JOptionPane.showInputDialog("Digite a nova data de nascimento (AAAA-MM-DD) (ou pressione Enter para manter a atual):", dateFormat.format(contatoExistente.getData_nascimento()));
                if (!novaDataNascimentoStr.isEmpty()) {
                    try {
                        Date novaDataNascimento = dateFormat.parse(novaDataNascimentoStr);
                        contatoExistente.setData_nascimento(novaDataNascimento);
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato AAAA-MM-DD.");
                        return;
                    }
                }

                contatoController.updateContato(id, contatoExistente);
                JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Contato não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar contato: " + e.getMessage());
        }
    }

    private void excluirContato() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do contato que deseja excluir:");
        try {
            long id = Long.parseLong(idStr);
            long contatoIsPresent = contatoController.deleteContato(id);

            if (contatoIsPresent != 0) {
                JOptionPane.showMessageDialog(null, "Contato excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Contato não existe na base de dados.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Digite um número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato: " + e.getMessage());
        }
    }

    // No método main
    public static void main(String[] args) {
        ContatoController<Contato> contatoController = new ContatoControllerImpl();
        PrincipalMenu menu = new PrincipalMenu(contatoController);

        menu.exibirMenu();
    }
}
