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
        List<Prato> cardapio = restaurante.getCardapio();
        for (int i = 0; i < cardapio.size(); i++) {
            Prato prato = cardapio.get(i);
            System.out.println((i + 1) + " -> " + prato.getNome() + " (" + prato.getDescricao() + ") - Valor: " + prato.getPreco());
        }

        System.out.print("Número do prato: ");
        int numeroPrato = scanner.nextInt();
        scanner.nextLine();

        if (numeroPrato < 1 || numeroPrato > cardapio.size()) {
            System.out.println("Número de prato inválido.");
            return;
        }

        Prato prato = cardapio.get(numeroPrato - 1);

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
    }

    private void removerPratoDoPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum prato adicionado ao pedido.");
            return;
        }

        System.out.println("\nPratos no pedido:");
        List<ItemPedido> itens = pedido.getItens();
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            System.out.println((i + 1) + " -> " + item.getPrato().getNome() + " (" + item.getQuantidade() + "x)");
        }

        System.out.print("Número do prato a remover: ");
        int numeroPrato = scanner.nextInt();
        scanner.nextLine();

        if (numeroPrato < 1 || numeroPrato > itens.size()) {
            System.out.println("Número de prato inválido.");
            return;
        }

        Prato prato = itens.get(numeroPrato - 1).getPrato();
        pedido.removerItem(prato);
        System.out.println("Prato removido com sucesso!");
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

    public void listarTodosPedidos() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        System.out.println("\nLista de todos os pedidos:");
        restaurante.getPedidos().forEach(System.out::println);
    }

    public void visualizarPedidoEspecifico() {
        try {
            if (restaurante.getPedidos().isEmpty()) {
                System.out.println("Nenhum pedido registrado.");
                return;
            }

            System.out.println("\nNúmero dos pedidos cadastrados:");
            restaurante.getPedidos().forEach(p -> System.out.println("-> " + p.getNumeroPedido()));

            System.out.print("\nDigite o número do pedido que deseja visualizar: ");
            int numeroPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = restaurante.getPedidos().stream()
                    .filter(p -> p.getNumeroPedido() == numeroPedido)
                    .findFirst()
                    .orElse(null);

            if (pedido != null) {
                System.out.println("\nDetalhes do pedido:");
                System.out.println(pedido);
            } else {
                System.out.println("Pedido não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void editarPedidoEspecifico() {
        try {
            if (restaurante.getPedidos().isEmpty()) {
                System.out.println("Nenhum pedido registrado.");
                return;
            }

            System.out.println("\nNúmero dos pedidos cadastrados:");
            restaurante.getPedidos().forEach(p -> System.out.println("-> " + p.getNumeroPedido()));

            System.out.print("\nDigite o número do pedido que deseja editar: ");
            int numeroPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = restaurante.getPedidos().stream()
                    .filter(p -> p.getNumeroPedido() == numeroPedido)
                    .findFirst()
                    .orElse(null);

            if (pedido == null) {
                System.out.println("Pedido não encontrado.");
                return;
            }

            while (true) {
                System.out.println("\n1 - Adicionar Prato");
                System.out.println("2 - Remover Prato");
                System.out.println("3 - Listar Pedido");
                System.out.println("4 - Finalizar Edição");
                System.out.println("5 - Cancelar Edição");
                System.out.print("Escolha: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> adicionarPratoAoPedido(pedido);
                    case 2 -> removerPratoDoPedido(pedido);
                    case 3 -> listarPedido(pedido);
                    case 4 -> {
                        System.out.println("Edição finalizada.");
                        return;
                    }
                    case 5 -> {
                        System.out.println("Edição cancelada.");
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

    public void removerPedidoEspecifico() {
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