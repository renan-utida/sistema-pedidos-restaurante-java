package org.example;

import java.util.Scanner;

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

        System.out.print("Nome do prato a remover: ");
        String nome = scanner.nextLine();
        if (!nome.matches("[\\p{L}\\s]+")) {
            System.out.println("Nome deve conter apenas letras e espaços.");
            return;
        }

        Prato prato = restaurante.getCardapio().stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst()
                .orElse(null);

        if (prato != null) {
            restaurante.removerPrato(prato);
            System.out.println("Prato removido com sucesso!");
        } else {
            System.out.println("Prato não encontrado.");
        }
    }

    public void listarCardapio() {
        if (restaurante.getCardapio().isEmpty()) {
            System.out.println("Nenhum prato cadastrado no cardápio.");
            return;
        }
        restaurante.getCardapio().forEach(System.out::println);
    }
}