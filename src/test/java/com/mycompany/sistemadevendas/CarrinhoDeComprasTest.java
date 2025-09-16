/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sistemadevendas;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mateu
 */
public class CarrinhoDeComprasTest {
    
    @Test
    void testAdicionarProduto() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Produto produto = new Produto(1, "Camisa", 100.0, 10);

        carrinho.adicionarProduto(produto, 2);

        assertEquals(1, carrinho.getItens().size());
    }

    @Test
    void testRemoverProduto() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Produto produto = new Produto(1, "Camisa", 100.0, 10);

        carrinho.adicionarProduto(produto, 2);
        carrinho.removerProduto(1);

        assertEquals(0, carrinho.getItens().size());
    }

    @Test
    void testCalcularTotal() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarProduto(new Produto(1, "Camisa", 100.0, 10), 2);
        carrinho.adicionarProduto(new Produto(2, "Calça", 150.0, 5), 1);

        assertEquals(350.0, carrinho.calcularTotal());
    }
    
}
