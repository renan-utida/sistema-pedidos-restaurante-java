package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ItemPedido {
    private Prato prato;
    private int quantidade;
    private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    public ItemPedido(Prato prato, int quantidade) {
        this.prato = prato;
        this.quantidade = quantidade;
    }

    public Prato getPrato() {
        return prato;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return prato.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "prato=" + prato.getNome() +
                ", quantidade=" + quantidade +
                ", subtotal=" + df.format(getSubtotal()).replace(",", ".") +
                '}';
    }
}