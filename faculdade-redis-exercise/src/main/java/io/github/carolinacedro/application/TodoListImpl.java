package io.github.carolinacedro.application;

import redis.clients.jedis.Jedis;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class TodoListImpl implements TodoList {

    private final Jedis jedis = new Jedis();
    private static final Scanner sc = new Scanner(System.in);

    @Override
    public void getAllTasks() {
        Set<String> keys = jedis.keys("id:*");
        for (String key : keys) {
            String description = jedis.get(key);
            System.out.println("\n");
            System.out.println("Tarefa: ");
            System.out.println("Chave: " + key);
            System.out.println("Descricao: " + description);
        }
    }

    @Override
    public Optional<String> findTaskById() {
        System.out.println("Entre com a id da task: ");
        String id = sc.nextLine();
        String description = jedis.get("id:" + id);
        return Optional.ofNullable(description);
    }

    @Override
    public void delete() {
        System.out.println("Entre com o id que sera deletado: ");
        String id = sc.nextLine();
        jedis.del("id:" + id);
        System.out.println("Existe dados cadastrados no redis: " + this.jedis);
        System.out.println("Dado com a chave " + id + " Deletado");
    }

    @Override
    public String create() {
        System.out.println("defina um id: ");
        String id = sc.nextLine();
        System.out.println("defina uma descricao: ");
        String description = sc.nextLine();

        jedis.set("id:" + id, description);
        return "Registro criado";
    }

    public void allDataDrop() {
        Set<String> keys = jedis.keys("id:*");
        for (String key : keys) {
            jedis.del(key);
        }
        System.out.println("Todos os dados foram deletados do Redis");
    }

    public void menu() {

        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n");
            System.out.println("Cadastro de tarefas");
            System.out.println("Menu");
            System.out.println("(1) criar tarefa");
            System.out.println("(2) listar tarefas");
            System.out.println("(3) apagar tarefa");
            System.out.println("(4) apagar todas as tarefa");
            System.out.println("(0) sair");
            op = sc.nextInt();

            switch (op) {
                case 1 -> create();
                case 2 -> getAllTasks();
                case 3 -> delete();
                case 4 -> allDataDrop();
                default -> System.out.println("Opcao invalida");
            }

        } while (op != 0);

        System.out.println("\n");
    }

    public static void main(String[] args) {
        TodoListImpl todoList = new TodoListImpl();
        todoList.menu();
        todoList.closeRedisInstance();
    }


    private Jedis redisInstance = new Jedis("localhost", 6380);

    public void closeRedisInstance() {
        redisInstance.close();
    }

}
