package org.example;

import java.util.*;

public class MenuPedido {
    private Scanner scanner;
    private Pedido pedido;
    private Restaurante restaurante;
    private GerenciarPedidos gerenciarPedidos;

    public MenuPedido(Scanner scanner, Pedido pedido, Restaurante restaurante, GerenciarPedidos gerenciarPedidos) {
        this.scanner = scanner;
        this.pedido = pedido;
        this.restaurante = restaurante;
        this.gerenciarPedidos = gerenciarPedidos;
    }

    public void exibirMenu() {
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
                    case 1 -> adicionarPratoAoPedido();
                    case 2 -> removerPratoDoPedido();
                    case 3 -> listarPedido();
                    case 4 -> {
                        if (finalizarPedido()) {
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
    }

    public void exibirMenuEdicao() {
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
                    case 1 -> adicionarPratoAoPedido();
                    case 2 -> removerPratoDoPedido();
                    case 3 -> listarPedido();
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
    }

    private void adicionarPratoAoPedido() {
        try {
            System.out.println("\nPratos disponíveis:");
            Utilidades.exibirListaPratos(restaurante.getCardapio());

            System.out.print("Número do prato: ");
            int numeroPrato = scanner.nextInt();
            scanner.nextLine();

            List<Prato> cardapio = restaurante.getCardapio();

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

    private void removerPratoDoPedido() {
        try {
            if (pedido.getItens().isEmpty()) {
                System.out.println("Nenhum prato adicionado ao pedido.");
                return;
            }

            System.out.println("\nPratos no pedido:");
            Utilidades.exibirListaItensPedido(pedido.getItens());

            System.out.print("Número do prato a remover: ");
            int numeroPrato = scanner.nextInt();
            scanner.nextLine();

            List<ItemPedido> itens = pedido.getItens();

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

    private void listarPedido() {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum prato adicionado ao pedido.");
        } else {
            System.out.println("\nDetalhes do pedido:");
            gerenciarPedidos.exibirPedidoFormatado(pedido);
        }
    }

//    private void listarPedido(Pedido pedido) {
//        if (pedido.getItens().isEmpty()) {
//            System.out.println("Nenhum prato adicionado ao pedido.");
//        } else {
//            System.out.println("\nDetalhes do pedido:");
//            exibirPedidoFormatado(pedido);
//        }
//    }

    private boolean finalizarPedido() {
        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: Adicione pelo menos um prato para finalizar o pedido.");
            return false;
        } else {
            restaurante.criarPedido(pedido);
            System.out.println("\nPedido finalizado com sucesso! Valor total a ser pago: R$ " + pedido.calcularTotal());
            return true;
        }
    }

//    private boolean finalizarPedido(Pedido pedido) {
//        if (pedido.getItens().isEmpty()) {
//            System.out.println("Erro: Adicione pelo menos um prato para finalizar o pedido.");
//            return false;
//        } else {
//            restaurante.criarPedido(pedido);
//            System.out.println("\nPedido finalizado com sucesso! Valor total a ser pago: R$ " + df.format(pedido.calcularTotal()));
//            return true;
//        }
//    }
}