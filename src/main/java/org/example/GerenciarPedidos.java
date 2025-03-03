package org.example;

import java.util.Scanner;

public class GerenciarPedidos {
    private Restaurante restaurante;
    private Scanner scanner;

    public GerenciarPedidos(Restaurante restaurante, Scanner scanner) {
        this.restaurante = restaurante;
        this.scanner = scanner;
    }

    public void criarPedido() {
        System.out.print("Número do pedido: ");
        int numeroPedido = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do cliente: ");
        String cliente = scanner.nextLine();

        Pedido pedido = new Pedido(numeroPedido, cliente);

        while (true) {
            System.out.println("\n1 - Adicionar Prato");
            System.out.println("2 - Finalizar Pedido");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome do prato: ");
                String nomePrato = scanner.nextLine();
                Prato prato = restaurante.getCardapio().stream()
                        .filter(p -> p.getNome().equals(nomePrato))
                        .findFirst()
                        .orElse(null);

                if (prato != null) {
                    pedido.adicionarPrato(prato);
                    System.out.println("Prato adicionado ao pedido.");
                } else {
                    System.out.println("Prato não encontrado no cardápio.");
                }
            } else if (opcao == 2) {
                restaurante.criarPedido(pedido);
                System.out.println("Pedido finalizado com sucesso!");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    public void visualizarPedido() {
        System.out.print("Número do pedido: ");
        int numeroPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = restaurante.getPedidos().stream()
                .filter(p -> p.getNumeroPedido() == numeroPedido)
                .findFirst()
                .orElse(null);

        if (pedido != null) {
            System.out.println(pedido);
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void listarPedidos() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        restaurante.getPedidos().forEach(System.out::println);
    }
}