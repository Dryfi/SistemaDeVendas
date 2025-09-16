/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sistemadevendas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author mateu
 */
public class DescontoServiceTest {
    
    @Test
    void testDesconto10Porcento() {
        DescontoService descontoService = mock(DescontoService.class);
        when(descontoService.aplicarDesconto(100.0)).thenReturn(90.0);

        assertEquals(90.0, descontoService.aplicarDesconto(100.0));
    }

    @Test
    void testDescontoZero() {
        DescontoService descontoService = mock(DescontoService.class);
        when(descontoService.aplicarDesconto(100.0)).thenReturn(100.0);

        assertEquals(100.0, descontoService.aplicarDesconto(100.0));
    }
}
