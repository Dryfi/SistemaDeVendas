/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadevendas;

import java.util.*;
/**
 *
 * @author mateu
 */
public class CarrinhoDeCompras {
   private Map<Integer, ItemCarrinho> itens = new HashMap<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.put(produto.getId(), new ItemCarrinho(produto, quantidade));
    }

    public void removerProduto(int produtoId) {
        itens.remove(produtoId);
    }

    public double calcularTotal() {
        return itens.values().stream()
            .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
            .sum();
    }

    public Collection<ItemCarrinho> getItens() {
        return itens.values();
    }

    static class ItemCarrinho {
        private Produto produto;
        private int quantidade;

        public ItemCarrinho(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() { return produto; }
        public int getQuantidade() { return quantidade; }
    }  
}
