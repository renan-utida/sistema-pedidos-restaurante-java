package org.example;

import java.util.*;

public class GerenciarPedidos {
    private Restaurante restaurante;
    private Scanner scanner;

    public GerenciarPedidos(Restaurante restaurante, Scanner scanner) {
        this.restaurante = restaurante;
        this.scanner = scanner;
    }

    public void criarPedido() {
        try{
            System.out.print("\nNúmero do pedido: ");
            int numeroPedido = scanner.nextInt();
            scanner.nextLine();

            boolean pedidoExistente = restaurante.getPedidos().stream()
                    .anyMatch(p -> p.getNumeroPedido() == numeroPedido);

            if (pedidoExistente) {
                System.out.println("Erro: Já existe um pedido com esse número.");
                return;
            } else if (numeroPedido <= 0){
                System.out.println("Erro: O número do pedido deve ser maior que zero!");
                return;
            }

            System.out.print("Nome do cliente: ");
            String cliente = scanner.nextLine().trim();
            if (!cliente.matches("[\\p{L}\\s]+")) {
                System.out.println("Erro: Nome do cliente inválido.");
                return;
            } else if (cliente.isEmpty()) {
                System.out.println("Erro: O nome do cliente não pode estar vazio.\\n\"");
                return;
            }

            Pedido pedido = new Pedido(numeroPedido, cliente);

            while (true) {
                System.out.println("\n1 - Adicionar Prato");
                System.out.println("2 - Remover Prato");
                System.out.println("3 - Listar Pedido");
                System.out.println("4 - Finalizar Pedido");
                System.out.println("5 - Cancelar Pedido");
                System.out.print("Escolha: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> adicionarPratoAoPedido(pedido);
                    case 2 -> removerPratoDoPedido(pedido);
                    case 3 -> listarPedido(pedido);
                    case 4 -> {
                        finalizarPedido(pedido);
                        return;
                    }
                    case 5 -> {
                        System.out.println("Pedido cancelado.");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 5.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------------------
    // Métodos de Criar Pedido

    private void adicionarPratoAoPedido(Pedido pedido) {
        System.out.println("\nPratos disponíveis:");
        restaurante.getCardapio().forEach(p -> System.out.println("- " + p.getNome()));
        System.out.println();

        System.out.print("Nome do prato: ");
        String nomePrato = scanner.nextLine();
        Prato prato = restaurante.getCardapio().stream()
                .filter(p -> p.getNome().equals(nomePrato))
                .findFirst()
                .orElse(null);

        if (prato != null) {
            while (true) {
                try {
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    if (quantidade <= 0) {
                        System.out.println("Erro: A quantidade deve ser maior que zero.");
                        continue;
                    }
                    pedido.adicionarItem(prato, quantidade);
                    System.out.println("Prato adicionado ao pedido.");
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite um número válido.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Prato não encontrado no cardápio.");
        }
    }

    private void removerPratoDoPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum prato adicionado ao pedido.");
            return;
        }

        System.out.println("\nPratos no pedido:");
        pedido.getItens().forEach(item -> System.out.println("- " + item.getPrato().getNome()));

        System.out.print("Nome do prato a remover: ");
        String nomePrato = scanner.nextLine();

        Prato prato = pedido.getItens().stream()
                .filter(item -> item.getPrato().getNome().equals(nomePrato))
                .map(ItemPedido::getPrato)
                .findFirst()
                .orElse(null);

        if (prato != null) {
            pedido.removerItem(prato);
            System.out.println("Prato removido com sucesso!");
        } else {
            System.out.println("Prato não encontrado no pedido.");
        }
    }

    private void listarPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum prato adicionado ao pedido.");
        } else {
            System.out.println("\nDetalhes do pedido:");
            System.out.println(pedido);
        }
    }

    private void finalizarPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: Adicione pelo menos um prato para finalizar o pedido.");
        } else {
            restaurante.criarPedido(pedido);
            System.out.println("Pedido finalizado com sucesso!");
        }
    }

    //--------------------------------------------------------------------------------------------
    // Métodos de Principal.java

    public void visualizarAlgumPedido() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

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

    public void listarTodosPedidos() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        restaurante.getPedidos().forEach(System.out::println);
    }

    public void removerAlgumPedido() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        System.out.print("Número do pedido a remover: ");
        int numeroPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = restaurante.getPedidos().stream()
                .filter(p -> p.getNumeroPedido() == numeroPedido)
                .findFirst()
                .orElse(null);

        if (pedido != null) {
            restaurante.getPedidos().remove(pedido);
            restaurante.salvarDados();
            System.out.println("Pedido removido com sucesso!");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void limparTodosPedidos() {
        restaurante.getPedidos().clear();
        restaurante.salvarDados();
        System.out.println("Todos os pedidos foram removidos.");
    }
}