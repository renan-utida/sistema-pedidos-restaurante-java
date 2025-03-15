package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Utilidades {
    private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    public static String formatarValor(double valor) {
        return df.format(valor);
    }

    public static void exibirListaPratos(List<Prato> pratos) {
        for (int i = 0; i < pratos.size(); i++) {
            Prato prato = pratos.get(i);
            System.out.println((i + 1) + " -> " + prato.getNome() + " (" + prato.getDescricao() + ") - Valor: " + formatarValor(prato.getPreco()));
        }
    }

    public static void exibirListaItensPedido(List<ItemPedido> itens) {
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            System.out.println((i + 1) + " -> " + item.getPrato().getNome() + " (" + item.getQuantidade() + "x)");
        }
    }

    public static void mostrarNumerosPedidosCadastrados(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        System.out.println("\nNº Pedidos Cadastrados até o momento: ");
        System.out.print("-> ");
        List<Integer> numerosPedidos = pedidos.stream()
                .map(Pedido::getNumeroPedido)
                .sorted()
                .toList();

        for (int i = 0; i < numerosPedidos.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(numerosPedidos.get(i));
        }
        System.out.println();
    }
}