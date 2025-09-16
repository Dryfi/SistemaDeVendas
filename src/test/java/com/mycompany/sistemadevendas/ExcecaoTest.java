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
public class ExcecaoTest {
  @Test
    void testExcecaoPagamento() {
        Produto produto = new Produto(1, "Notebook", 3000.0, 2);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarProduto(produto, 1);

        DescontoService desconto = mock(DescontoService.class);
        when(desconto.aplicarDesconto(anyDouble())).thenReturn(3000.0);

        PagamentoService pagamento = mock(PagamentoService.class);
        when(pagamento.processarPagamento(anyDouble()))
                .thenThrow(new RuntimeException("Erro no pagamento"));

        NotaFiscalService nota = mock(NotaFiscalService.class);

        VendaService vendaService = new VendaService(desconto, pagamento, nota);
        boolean resultado = vendaService.realizarVenda(carrinho);

        assertFalse(resultado);
        assertEquals(2, produto.getEstoque());
        verify(nota, never()).emitirNota(anyDouble(), anyList());
    }
}
