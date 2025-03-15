package org.example;

import java.util.*;

public class LeituraDeDados {
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

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
