package org.example;

import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GerenciarPedidos {
    private Restaurante restaurante;
    private Scanner scanner;
    private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    public GerenciarPedidos(Restaurante restaurante, Scanner scanner) {
        this.restaurante = restaurante;
        this.scanner = scanner;
    }

    public void criarPedido() {
        try{
            mostrarNumerosPedidosCadastrados();
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
            if (cliente.isEmpty()) {
                System.out.println("Erro: O nome do cliente não pode estar vazio.");
                return;
            } else if (!cliente.matches("[\\p{L}\\s]+")) {
                System.out.println("Erro: Nome do cliente inválido.");
                return;
            }

            Pedido pedido = new Pedido(numeroPedido, cliente);

            while (true) {
                try {
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
                            if (finalizarPedido(pedido)) {
                                return;
                            }
                        }
                        case 5 -> {
                            System.out.println("Pedido cancelado.");
                            return;
                        }
                        default -> System.out.println("Opção inválida! Digite um número de 1 a 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite um número válido.");
                    scanner.nextLine();
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
        try {
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

            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser maior que zero.");
                return;
            }

            pedido.adicionarItem(prato, quantidade);
            System.out.println("Prato adicionado ao pedido.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido.");
            scanner.nextLine();
        }
    }

    private void removerPratoDoPedido(Pedido pedido) {
        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido.");
            scanner.nextLine();
        }
    }

    private void listarPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum prato adicionado ao pedido.");
        } else {
            System.out.println("\nDetalhes do pedido:");
            exibirPedidoFormatado(pedido);
        }
    }

    private boolean finalizarPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: Adicione pelo menos um prato para finalizar o pedido.");
            return false;
        } else {
            restaurante.criarPedido(pedido);
            System.out.println("\nPedido finalizado com sucesso! Valor total a ser pago: R$ " + df.format(pedido.calcularTotal()));
            return true;
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
        restaurante.getPedidos().forEach(this::exibirPedidoFormatado);
    }

    public void visualizarPedidoEspecifico() {
        try {
            if (restaurante.getPedidos().isEmpty()) {
                System.out.println("Nenhum pedido registrado.");
                return;
            }

            mostrarNumerosPedidosCadastrados();

            System.out.print("\nDigite o número do pedido que deseja visualizar: ");
            int numeroPedido = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = restaurante.getPedidos().stream()
                    .filter(p -> p.getNumeroPedido() == numeroPedido)
                    .findFirst()
                    .orElse(null);

            if (pedido != null) {
                exibirPedidoFormatado(pedido);
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

            mostrarNumerosPedidosCadastrados();

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
                try {
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
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite um número válido.");
                    scanner.nextLine();
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

        System.out.println("\nPedidos cadastrados:");
        restaurante.getPedidos().forEach(this::exibirPedidoFormatado);

        System.out.print("\nNúmero do pedido a remover: ");
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

    // ---------------------------------------------------------------
    // Métodos Auxiliares

    private void mostrarNumerosPedidosCadastrados() {
        if (restaurante.getPedidos().isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        System.out.println("\nNº Pedidos Cadastrados até o momento: ");
        System.out.print("-> ");
        List<Integer> numerosPedidos = restaurante.getPedidos().stream()
                .map(Pedido::getNumeroPedido)
                .sorted()
                .toList();

        for (int i = 0; i < numerosPedidos.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(numerosPedidos.get(i));
        }
        System.out.println();
    }

    private void exibirPedidoFormatado(Pedido pedido) {
        System.out.println("\n-> Pedido Nº " + pedido.getNumeroPedido() + " (" + pedido.getCliente() + "):");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("   - " + item.getPrato().getNome() + " (" + item.getPrato().getDescricao() + "), Quantidade = " + item.getQuantidade() + ", Subtotal = R$ " + df.format(item.getSubtotal()));
        }
        System.out.println("   $ Total = R$ " + df.format(pedido.calcularTotal()));
    }
}