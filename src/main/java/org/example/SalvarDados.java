package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalvarDados {
    private static final String ARQUIVO = "restaurante.txt";

    public void salvar(List<Prato> cardapio, List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Prato prato : cardapio) {
                writer.write("Prato: {nome=" + prato.getNome() + ", preco=" + prato.getPreco() + ", descricao=" + prato.getDescricao() + "}");
                writer.newLine();
            }
            for (Pedido pedido : pedidos) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String data = sdf.format(new Date());
                writer.write("Pedido: {numeroPedido=" + pedido.getNumeroPedido() + ", cliente=" + pedido.getCliente() + ", data=" + data + "}");
                writer.newLine();
                for (Prato prato : pedido.getListaDePratos()) {
                    writer.write("  Prato: {nome=" + prato.getNome() + "}");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
