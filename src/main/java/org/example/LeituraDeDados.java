package org.example;

import java.util.Scanner;

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
}
