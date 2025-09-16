/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sistemadevendas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author mateu
 */
public class PagamentoTest {
 
    @Test
    void testPagamentoAprovado() {
        Produto produto = new Produto(1, "Mouse", 50.0, 10);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarProduto(produto, 2);

        DescontoService desconto = mock(DescontoService.class);
        when(desconto.aplicarDesconto(anyDouble())).thenReturn(90.0);

        PagamentoService pagamento = mock(PagamentoService.class);
        when(pagamento.processarPagamento(90.0)).thenReturn(true);

        NotaFiscalService nota = mock(NotaFiscalService.class);

        VendaService vendaService = new VendaService(desconto, pagamento, nota);
        boolean resultado = vendaService.realizarVenda(carrinho);

        assertTrue(resultado);
        assertEquals(8, produto.getEstoque());
        verify(nota, times(1)).emitirNota(eq(90.0), anyList());
    }

    @Test
    void testPagamentoNegado() {
        Produto produto = new Produto(1, "Monitor", 500.0, 5);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarProduto(produto, 1);

        DescontoService desconto = mock(DescontoService.class);
        when(desconto.aplicarDesconto(anyDouble())).thenReturn(500.0);

        PagamentoService pagamento = mock(PagamentoService.class);
        when(pagamento.processarPagamento(500.0)).thenReturn(false);

        NotaFiscalService nota = mock(NotaFiscalService.class);

        VendaService vendaService = new VendaService(desconto, pagamento, nota);
        boolean resultado = vendaService.realizarVenda(carrinho);

        assertFalse(resultado);
        assertEquals(5, produto.getEstoque());
        verify(nota, never()).emitirNota(anyDouble(), anyList());
    }
}
