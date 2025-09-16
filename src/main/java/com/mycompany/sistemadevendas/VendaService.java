/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadevendas;

import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author mateu
 */
public class VendaService {
 private DescontoService descontoService;
 private PagamentoService pagamentoService;
 private NotaFiscalService notaFiscalService;

    public VendaService(DescontoService descontoService, PagamentoService pagamentoService, NotaFiscalService notaFiscalService) {
        this.descontoService = descontoService;
        this.pagamentoService = pagamentoService;
        this.notaFiscalService = notaFiscalService;
    }

    public boolean realizarVenda(CarrinhoDeCompras carrinho) {
        double total = carrinho.calcularTotal();
        double valorComDesconto = descontoService.aplicarDesconto(total);

        try {
            for (CarrinhoDeCompras.ItemCarrinho item : carrinho.getItens()) {
                if (item.getProduto().getEstoque() < item.getQuantidade()) {
                    return false;
                }
            }

            boolean pago = pagamentoService.processarPagamento(valorComDesconto);
            if (!pago) return false;

            for (CarrinhoDeCompras.ItemCarrinho item : carrinho.getItens()) {
                item.getProduto().reduzirEstoque(item.getQuantidade());
            }

            List<Produto> produtos = carrinho.getItens().stream()
                .map(CarrinhoDeCompras.ItemCarrinho::getProduto)
                .collect(Collectors.toList());

            notaFiscalService.emitirNota(valorComDesconto, produtos);
            return true;
        } catch (Exception e) {
            return false;
        }
    }   
}
