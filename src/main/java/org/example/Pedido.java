package org.example;

import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Pedido {
    private int numeroPedido;
    private String cliente;
    private List<ItemPedido> itens;
    private double total;
    private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    public Pedido(int numeroPedido, String cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public int getNumeroPedido(){
        return numeroPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(Prato prato, int quantidade) {
        Optional<ItemPedido> itemExistente = itens.stream()
                .filter(i -> i.getPrato().equals(prato))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemPedido item = itemExistente.get();
            item = new ItemPedido(prato, item.getQuantidade() + quantidade);
            itens.remove(itemExistente.get());
            itens.add(item);
        } else {
            itens.add(new ItemPedido(prato, quantidade));
        }
        total += prato.getPreco() * quantidade;
    }

    public void removerItem(Prato prato) {
        ItemPedido item = itens.stream()
                .filter(i -> i.getPrato().equals(prato))
                .findFirst()
                .orElse(null);
        if (item != null) {
            itens.remove(item);
            total -= item.getSubtotal();
        }
    }

    public double calcularTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{")
                .append("numeroPedido=").append(numeroPedido)
                .append(", cliente='").append(cliente).append('\'')
                .append(", itens=[");
        for (ItemPedido item : itens) {
            sb.append("\n  ").append(item);
        }
        sb.append("\n], total=").append(df.format(total).replace(",", ".")).append('}');
        return sb.toString();
    }
}