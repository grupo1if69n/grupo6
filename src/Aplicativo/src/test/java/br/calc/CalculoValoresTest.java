/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calc;

import br.jpa.entity.Conta;
import br.jpa.entity.UsuarioConta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import teste.cenarios.CenarioTeste;

/**
 *
 * @author grupo1if69n
 */
public class CalculoValoresTest {
    
    private Conta conta;
    
    public CalculoValoresTest() {
    }
    
    @Before
    public void setUp() {
        conta = CenarioTeste.getCenarioTeste1();
    }
    
    /*
    Verificar se a soma dos valores individuais equivale ao valor total da conta
    */
    @Test
    public void testCalculoValorTotalEIndividual() {
        System.err.println("\ntestCalculoValorTotalEIndividual:");
        //O objeto criado foi o de TaxaServicoIgual, apenas para acessa os
        //métodos da superclasse
        CalculoValores calculoValores = new TaxaServicoIgual();
        Conta contaAtualizada;
        
        //Obter o valor total da conta
        contaAtualizada = calculoValores.calculoValorTotal(conta);
        
        //Obter os valores individuais
        contaAtualizada = calculoValores.calculoValorIndividual(contaAtualizada);
        
        double soma = 0.0;
        
        for (UsuarioConta usuarioConta : contaAtualizada.getUsuarioContaCollection()) {
            soma += usuarioConta.getUCValor();
        }
        
        System.err.println("Total: " + contaAtualizada.getCValor());
        System.err.println("Soma: " + soma);
        
        assertEquals(contaAtualizada.getCValor(), soma, 0.0001);
    }
    
}
