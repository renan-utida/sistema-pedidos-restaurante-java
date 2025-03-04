package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarregarDados {
    private static final String ARQUIVO = "restaurante.txt";

    public void carregar(List<Prato> cardapio, List<Pedido> pedidos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Prato:")) {
                    String[] partes = linha.substring(7).split(", ");
                    String nome = partes[0].split("=")[1];
                    double preco = Double.parseDouble(partes[1].split("=")[1]);
                    String descricao = partes[2].split("=")[1].replace("}", "");
                    cardapio.add(new Prato(nome, preco, descricao));
                } else if (linha.startsWith("Pedido:")) {
                    String[] partes = linha.substring(8).split(", ");
                    int numeroPedido = Integer.parseInt(partes[0].split("=")[1]);
                    String cliente = partes[1].split("=")[1];
                    String data = partes[2].split("=")[1];
                    Pedido pedido = new Pedido(numeroPedido, cliente);

                    // Carregar pratos do pedido
                    while ((linha = reader.readLine()) != null && linha.startsWith("  Prato:")) {
                        String[] pratoPartes = linha.substring(9).split(", ");
                        String nomePrato = pratoPartes[0].split("=")[1];
                        Prato prato = cardapio.stream().filter(p -> p.getNome().equals(nomePrato)).findFirst().orElse(null);
                        if (prato != null) {
                            pedido.adicionarPrato(prato);
                        }
                    }
                    pedidos.add(pedido);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum dado anterior encontrado.");
        }
    }
}