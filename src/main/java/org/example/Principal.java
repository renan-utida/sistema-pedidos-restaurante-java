package org.example;

import java.util.*;

public class Principal {
    private static Scanner scanner;
    private static Restaurante restaurante = new Restaurante();
    private static GerenciarCardapio gerenciarCardapio;
    private static GerenciarPedidos gerenciarPedidos;

    public Principal() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.gerenciarCardapio = new GerenciarCardapio(restaurante, scanner);
        this.gerenciarPedidos = new GerenciarPedidos(restaurante, scanner);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        System.out.println("Bem-vindo ao sistema de pedidos do restaurante!");
        while (true) {
            try {
                System.out.println("\n1 - Gerenciar Cardápio");
                System.out.println("2 - Gerenciar Pedidos");
                System.out.println("3 - Encerrar");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarCardapio();
                    case 2 -> gerenciarPedidos();
                    case 3 -> {
                        System.out.println("\nSistema encerrado. Obrigado!");
                        principal.closeScanner();
                        return;
                    }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void gerenciarCardapio() {
        while (true) {
            try {
                System.out.println("\n1 - Adicionar Prato");
                System.out.println("2 - Remover Prato");
                System.out.println("3 - Listar Cardápio");
                System.out.println("4 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarCardapio.adicionarPrato();
                    case 2 -> gerenciarCardapio.removerPrato();
                    case 3 -> gerenciarCardapio.listarCardapio();
                    case 4 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void gerenciarPedidos() {
        while (true) {
            try {
                System.out.println("\n1 - Criar Pedido");
                System.out.println("2 - Listar Todos Pedidos");
                System.out.println("3 - Visualizar Pedido Específico");
                System.out.println("4 - Editar Pedido Específico");
                System.out.println("5 - Remover Pedido Específico");
                System.out.println("6 - Limpar Todos os Pedidos");
                System.out.println("7 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarPedidos.criarPedido();
                    case 2 -> gerenciarPedidos.listarTodosPedidos();
                    case 3 -> gerenciarPedidos.visualizarPedidoEspecifico();
                    case 4 -> gerenciarPedidos.editarPedidoEspecifico();
                    case 5 -> gerenciarPedidos.removerPedidoEspecifico();
                    case 6 -> gerenciarPedidos.limparTodosPedidos();
                    case 7 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
