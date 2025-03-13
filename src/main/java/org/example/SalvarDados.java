package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalvarDados {
    private static final String ARQUIVO = "restaurante.txt";
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void salvar(List<Prato> cardapio, List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Prato prato : cardapio) {
                writer.write("Prato: {nome=" + prato.getNome() + ", preco=" + df.format(prato.getPreco()).replace(",", ".") + ", descricao=" + prato.getDescricao() + "}");
                writer.newLine();
            }
            for (Pedido pedido : pedidos) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String data = sdf.format(new Date());
                writer.write("Pedido: {numeroPedido=" + pedido.getNumeroPedido() + ", cliente=" + pedido.getCliente() + ", data=" + data + "}");
                writer.newLine();
                for (ItemPedido item : pedido.getItens()) {
                    writer.write("  Prato: {nome=" + item.getPrato().getNome() + ", quantidade=" + item.getQuantidade() + ", valor=" + df.format(item.getSubtotal()).replace(",", ".") + "}");
                    writer.newLine();
                }
                writer.write("  Total: {valor=" + df.format(pedido.calcularTotal()) + "}");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}