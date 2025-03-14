package org.example;

import java.util.*;

public class Restaurante {
    private List<Prato> cardapio;
    private List<Pedido> pedidos;
    private CarregarDados carregarDados = new CarregarDados();
    private SalvarDados salvarDados = new SalvarDados();

    public Restaurante() {
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        carregarDados.carregar(cardapio, pedidos);
    }

    public void adicionarPrato(Prato prato) {
        cardapio.add(prato);
        salvarDados.salvar(cardapio, pedidos);
    }

    public void removerPrato(Prato prato) {
        cardapio.remove(prato);
        salvarDados.salvar(cardapio, pedidos);
    }

    public void criarPedido(Pedido pedido) {
        pedidos.add(pedido);
        salvarDados.salvar(cardapio, pedidos);
    }

    public void salvarDados() {
        salvarDados.salvar(cardapio, pedidos);
    }

    public List<Prato> getCardapio() {
        return cardapio;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
