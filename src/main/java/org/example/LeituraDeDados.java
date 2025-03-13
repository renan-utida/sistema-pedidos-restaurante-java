package org.example;

import java.util.*;

public class LeituraDeDados {
    private static Scanner scanner = new Scanner(System.in);

    public static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Erro: Digite um número válido.");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Erro: Digite um número válido.");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double lerPrecoPrato(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer
                if (valor <= 0) {
                    System.out.println("Erro: O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Formato de preço inválido. Use números positivos inteiros ou decimais com ponto (ex: 4.99).\n");
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }

    public static int lerInteiroPositivo(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Erro: O número do pedido não pode estar vazio.\n");
                    continue;
                }
                int numero = Integer.parseInt(input);
                if (numero <= 0) {
                    System.out.println("Erro: O número do pedido deve ser maior que zero.\n");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.\n");
            }
        }
    }

    public static String lerNomeCliente(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Erro: O nome do cliente não pode estar vazio.\n");
                continue;
            } else if (!nome.matches("[\\p{L}\\s]+")) {
                System.out.println("Erro: O nome deve conter apenas letras e espaços.\n");
                continue;
            }
            return nome;
        }
    }
}
