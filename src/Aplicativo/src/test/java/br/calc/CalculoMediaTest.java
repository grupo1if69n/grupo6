/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calc;

import br.jpa.entity.UsuarioConta;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import teste.cenarios.CenarioTeste;

/**
 *
 * @author grupo1if69n
 */
public class CalculoMediaTest {
    
    private Collection<UsuarioConta> usuarioContas;
    
    public CalculoMediaTest() {
    }
    
    @Before
    public void setUp() {
        usuarioContas = CenarioTeste.getCenarioTeste3();
    }

    @Test
    public void testCalculoMedia() {
        System.err.println("\ntestCalculoMedia:");
        
        double media = 0.0;
        
        //Soma os valores
        for (UsuarioConta usuarioConta : usuarioContas) {
            media += usuarioConta.getUCValor();
        }
        
        //Divide pela quantidade
        media /= usuarioContas.size();
        
        System.err.println("Média: " + media);
        System.err.println("Média calculo funcional: " + CalculoMedia.calculoMedia(usuarioContas));
        
        assertEquals(media, CalculoMedia.calculoMedia(usuarioContas), 0.0001);
    }
    
}
