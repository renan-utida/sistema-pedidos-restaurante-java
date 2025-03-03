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
        System.out.print("Preço do prato: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Descrição do prato: ");
        String descricao = scanner.nextLine();

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