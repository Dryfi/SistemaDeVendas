/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemadevendas;

import java.util.List;

/**
 *
 * @author mateu
 */
public interface NotaFiscalService {
    void emitirNota(double valor, List<Produto> produtos);

}
