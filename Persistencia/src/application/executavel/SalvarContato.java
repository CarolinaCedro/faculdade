package application.executavel;


import application.entities.Contato;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalvarContato {

    public static void main(String[] args) {
        List<Contato> contatos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Salvar contatos em binário");
            System.out.println("4 - Salvar contatos em XML");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

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
                    salvarContatos(contatos, "contatos.bin");
                    System.out.println("Contatos salvos em binário com sucesso.");
                    break;
                case 4:
                    salvarContatos(contatos, "contatos.xml");
                    System.out.println("Contatos salvos em XML com sucesso.");
                    break;
                case 5:
                    System.out.println("Saindo da aplicação.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    //Salvando em arquivo binario
    private static void salvarContatos(List<Contato> contatos, String nomeArquivo) {
        try {

            if (nomeArquivo.endsWith(".bin")) {
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
                    outputStream.writeObject(contatos);
                }
            } else if (nomeArquivo.endsWith(".xml")) {

                //Salvando em XML
                salvarContatosEmXml(contatos);
            } else {
                System.out.println("Formato de arquivo não suportado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    //Salvando em xml
    public static void salvarContatosEmXml(List<Contato> contatos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("Contatos");
            document.appendChild(rootElement);

            for (Contato contato : contatos) {
                Element contatoElement = document.createElement("Contato");

                Element nomeElement = document.createElement("Nome");
                nomeElement.appendChild(document.createTextNode(contato.getNome()));
                contatoElement.appendChild(nomeElement);

                Element telefoneElement = document.createElement("Telefone");
                telefoneElement.appendChild(document.createTextNode(contato.getNumeroTelefone()));
                contatoElement.appendChild(telefoneElement);

                Element emailElement = document.createElement("Email");
                emailElement.appendChild(document.createTextNode(contato.getEmail()));
                contatoElement.appendChild(emailElement);

                rootElement.appendChild(contatoElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);

            FileOutputStream outputStream = new FileOutputStream("contatos.xml");
            StreamResult result = new StreamResult(outputStream);

            transformer.transform(source, result);

            outputStream.close();
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            System.out.println("Erro ao salvar contatos em XML: " + e.getMessage());
        }
    }

}
