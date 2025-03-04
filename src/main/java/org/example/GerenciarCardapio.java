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
        System.out.print("Nome do prato: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        System.out.print("Preço do prato: ");
        String precoStr = scanner.nextLine().replace(",", ".");
        double preco;
        try {
            preco = Double.parseDouble(precoStr);
            if (preco <= 0) {
                throw new IllegalArgumentException("Preço deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Preço inválido. Use números decimais com ponto (ex: 14.99).");
        }

        System.out.print("Descrição do prato: ");
        String descricao = scanner.nextLine();
        if (descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }

        Prato prato = new Prato(nome, preco, descricao);
        restaurante.adicionarPrato(prato);
        System.out.println("Prato adicionado com sucesso!");
    }

    public void removerPrato() {
        System.out.print("Nome do prato a remover: ");
        String nome = scanner.nextLine();

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