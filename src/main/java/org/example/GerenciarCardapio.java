package org.example;

import java.util.*;

public class GerenciarCardapio {
    private Restaurante restaurante;
    private Scanner scanner;

    public GerenciarCardapio(Restaurante restaurante, Scanner scanner) {
        this.restaurante = restaurante;
        this.scanner = scanner;
    }

    public void adicionarPrato() {
        try {
            System.out.print("Nome do prato: ");
            String nome = scanner.nextLine();
            if (nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio.");
            } else if (!nome.matches("[\\p{L}\\s]+")) {
                throw new IllegalArgumentException("Nome deve conter apenas letras e espaços.");
            }

            double preco = LeituraDeDados.lerPrecoPrato("Preço do prato (use ponto como separador decimal, ex: 4.99): ");

            System.out.print("Descrição do prato: ");
            String descricao = scanner.nextLine();
            if (descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição não pode ser vazia.");
            }

            Prato prato = new Prato(nome, preco, descricao);
            restaurante.adicionarPrato(prato);
            System.out.println("Prato adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void removerPrato() {
        if (restaurante.getCardapio().isEmpty()) {
            System.out.println("Nenhum prato cadastrado no cardápio até o momento.");
            return;
        }

        Utilidades.exibirListaPratos(restaurante.getCardapio());
        System.out.print("Número do prato a remover: ");
        int numeroPrato = scanner.nextInt();
        scanner.nextLine();

        if (numeroPrato < 1 || numeroPrato > restaurante.getCardapio().size()) {
            System.out.println("Número de prato inválido.");
            return;
        }

        Prato prato = restaurante.getCardapio().get(numeroPrato - 1);
        restaurante.removerPrato(prato);
        System.out.println("Prato removido com sucesso!");
    }

    public void listarCardapio() {
        if (restaurante.getCardapio().isEmpty()) {
            System.out.println("Nenhum prato cadastrado no cardápio.");
            return;
        }
        Utilidades.exibirListaPratos(restaurante.getCardapio());
    }
}