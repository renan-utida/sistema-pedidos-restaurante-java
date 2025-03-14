package org.example;

import java.util.*;

public class LeituraDeDados {
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

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

    public static double lerPrecoPrato(String mensagem) throws IllegalArgumentException {
        try {
            System.out.print(mensagem);
            double valor = scanner.nextDouble();
            scanner.nextLine();
            if (valor <= 0) {
                throw new IllegalArgumentException("O valor deve ser maior que zero.");
            }
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new IllegalArgumentException("Formato de preço inválido. Use números positivos inteiros ou decimais com ponto (ex: 4.99).");
        }
    }
}
