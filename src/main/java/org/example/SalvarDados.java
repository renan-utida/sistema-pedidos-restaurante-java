package org.example;

import java.io.*;
import java.text.*;
import java.util.*;

public class SalvarDados {
    private static final String ARQUIVO = "restaurante.txt";
    private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    public void salvar(List<Prato> cardapio, List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Prato prato : cardapio) {
                writer.write("Prato: {nome=" + prato.getNome() + ", preco=" + df.format(prato.getPreco()).replace(",", ".") + ", descricao=" + prato.getDescricao() + "}");
                writer.newLine();
            }
            writer.write("------------------------------------------------------------------------------------------------");
            writer.newLine();
            for (Pedido pedido : pedidos) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String data = sdf.format(new Date());
                writer.write("Pedido: {numeroPedido=" + pedido.getNumeroPedido() + ", cliente=" + pedido.getCliente() + ", data=" + data + "}");
                writer.newLine();
                for (ItemPedido item : pedido.getItens()) {
                    writer.write("  Prato: {nome=" + item.getPrato().getNome() + ", quantidade=" + item.getQuantidade() + ", valor=" + df.format(item.getSubtotal()).replace(",", ".") + "}");
                    writer.newLine();
                }
                writer.write("  Total: {valor=" + df.format(pedido.calcularTotal()).replace(",", ".") + "}");
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}