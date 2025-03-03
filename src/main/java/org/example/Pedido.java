package org.example;

import java.util.*;

public class Pedido {
    private int numeroPedido;
    private String cliente;
    private List<Prato> listaDePratos;
    private double total;

    public Pedido(int numeroPedido, String cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.listaDePratos = new ArrayList<>();
        this.total = 0.0;
    }

    public void adicionarPrato(Prato prato) {
        listaDePratos.add(prato);
        total += prato.getPreco();
    }

    public void removerPrato(Prato prato) {
        if (listaDePratos.remove(prato)) {
            total -= prato.getPreco();
        }
    }

    public double calcularTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente='" + cliente + '\'' +
                ", listaDePratos=" + listaDePratos +
                ", total=" + total +
                '}';
    }
}