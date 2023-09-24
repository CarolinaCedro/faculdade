package application.binarios.entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalvarContatosEmBinario {

    public static void main(String[] args) {
        List<Contato> contatos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Salvar contatos em binário");
            System.out.println("4 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do contato:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o número de telefone:");
                    String numeroTelefone = scanner.nextLine();

                    System.out.println("Digite o e-mail:");
                    String email = scanner.nextLine();

                    Contato contato = new Contato(nome, numeroTelefone, email);
                    contatos.add(contato);
                    break;
                case 2:
                    System.out.println("Lista de contatos:");
                    for (Contato c : contatos) {
                        System.out.println(c);
                    }
                    break;
                case 3:
                    salvarContatosEmBinario(contatos);
                    System.out.println("Contatos salvos em binário com sucesso.");
                    break;
                case 4:
                    System.out.println("Saindo da aplicação.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void salvarContatosEmBinario(List<Contato> contatos) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("contatos.bin"))) {
            outputStream.writeObject(contatos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos em binário: " + e.getMessage());
        }
    }
}
