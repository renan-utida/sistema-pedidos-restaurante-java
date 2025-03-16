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
            Utilidades.mostrarNumerosPedidosCadastrados(restaurante.getPedidos());
            System.out.print("\nInsira um novo número de pedido: ");
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
            MenuPedido menuPedido = new MenuPedido(scanner, pedido, restaurante, this);
            menuPedido.exibirMenu();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

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

            Utilidades.mostrarNumerosPedidosCadastrados(restaurante.getPedidos());

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

            Utilidades.mostrarNumerosPedidosCadastrados(restaurante.getPedidos());

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

            MenuPedido menuPedido = new MenuPedido(scanner, pedido, restaurante, this);
            menuPedido.exibirMenuEdicao();
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

    // Metodo auxiliar para exibir um pedido formatado
    public void exibirPedidoFormatado(Pedido pedido) {
        System.out.println("\n-> Pedido Nº " + pedido.getNumeroPedido() + " (" + pedido.getCliente() + "):");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("   - " + item.getPrato().getNome() + " (" + item.getPrato().getDescricao() + "), Quantidade = " + item.getQuantidade() + ", Subtotal = R$ " + Utilidades.formatarValor(item.getSubtotal()));
        }
        System.out.println("   $ Total = R$ " + Utilidades.formatarValor(pedido.calcularTotal()));
    }
}