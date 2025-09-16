/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sistemadevendas;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author mateu
 */
public class EstoqueTest {
    @Test
    void testEstoqueInsuficiente() {
        Produto produto = new Produto(1, "Teclado", 150.0, 1);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarProduto(produto, 5); // estoque insuficiente

        DescontoService desconto = mock(DescontoService.class);
        when(desconto.aplicarDesconto(anyDouble())).thenReturn(100.0);

        PagamentoService pagamento = mock(PagamentoService.class);
        NotaFiscalService nota = mock(NotaFiscalService.class);

        VendaService vendaService = new VendaService(desconto, pagamento, nota);
        boolean resultado = vendaService.realizarVenda(carrinho);

        assertFalse(resultado);
        verify(pagamento, never()).processarPagamento(anyDouble());
        verify(nota, never()).emitirNota(anyDouble(), anyList());
    }  
}
