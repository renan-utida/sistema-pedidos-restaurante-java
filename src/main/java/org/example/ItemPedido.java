package org.example;

public class ItemPedido {
    private Prato prato;
    private int quantidade;

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
                ", subtotal=" + Utilidades.formatarValor(getSubtotal()).replace(",", ".") +
                '}';
    }
}