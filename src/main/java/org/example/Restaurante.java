package org.example;

import java.util.*;

public class Restaurante {
    private List<Prato> cardapio;
    private List<Pedido> pedidos;

    public Restaurante() {
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void adicionarPrato(Prato prato) {
        cardapio.add(prato);
    }

    public void removerPrato(Prato prato) {
        cardapio.remove(prato);
    }

    public void criarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Prato> getCardapio() {
        return cardapio;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
