/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calc;

import br.jpa.entity.Conta;
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
public class CalculoEstimativaTest {

    private Collection<Conta> contas;
    private Collection<UsuarioConta> usuarioContas;
    
    public CalculoEstimativaTest() {
    }

    @Before
    public void setUp() {
        contas = CenarioTeste.getCenarioTeste2();
        usuarioContas = CenarioTeste.getCenarioTeste3();
    }

    /*
    Verificar se o cálculo da estimativa total está sendo corretamente aplicado
    */
    @Test
    public void testCalculoEstimativaTotal() {
        System.err.println("\ntestCalculoEstimativaTotal:");
        
        double estimativaTotal = 0.0;
        
        //Soma os valores
        for (Conta conta : contas) {
            estimativaTotal += conta.getCValor();
        }
        
        //Divide pela quantidade
        estimativaTotal /= contas.size();
        
        System.err.println("Estimativa: " + estimativaTotal);
        System.err.println("Estimativa cálculo funcional: " + CalculoEstimativa.calculoEstimativaTotal(contas));
        
        assertEquals(estimativaTotal, CalculoEstimativa.calculoEstimativaTotal(contas), 0.0001);
    }

    /*
    Verificar se o cálculo da estimativa individual está sendo corretamente 
    aplicado
    */
    @Test
    public void testCalculoEstimativaIndividual() {
        System.err.println("\ntestCalculoEstimativaIndividual:");
        
        double estimativaIndividual = 0.0;
        
        //Soma os valores
        for (UsuarioConta usuarioConta : usuarioContas) {
            estimativaIndividual += usuarioConta.getUCValor();
        }
        
        //Divide pela quantidade
        estimativaIndividual /= usuarioContas.size();
        
        System.err.println("Estimativa: " + estimativaIndividual);
        System.err.println("Estimativa cálculo funcional: " + CalculoEstimativa.calculoEstimativaIndividual(usuarioContas));
        
        assertEquals(estimativaIndividual, CalculoEstimativa.calculoEstimativaIndividual(usuarioContas), 0.0001);
    }

}
