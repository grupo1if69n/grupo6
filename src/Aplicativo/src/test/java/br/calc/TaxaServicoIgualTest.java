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
public class TaxaServicoIgualTest {
    
    private Conta conta;
    
    public TaxaServicoIgualTest() {
    }
    
    @Before
    public void setUp() {
        conta = CenarioTeste.getCenarioTeste1();
    }

    /*
    Verifica se a soma de todos os valores individuais dos usuarios com a taxa 
    de serviço aplicada é igual ao valor total da conta com a taxa de serviço 
    aplicada.
    */
    @Test
    public void testCalculoTaxaServicoIgual1() {        
        System.err.println("\ntestCalculoTaxaServicoIgual1:");
        
        Conta contaAtualizada = new TaxaServicoIgual().atualizarValores(conta);
        double soma = 0.0;
        
        //Soma o total com a taxa de serviço aplicada
        for (UsuarioConta usuarioConta : contaAtualizada.getUsuarioContaCollection()) {
            soma += usuarioConta.getUCValor();
        }
        System.err.println("Total: " + contaAtualizada.getCValor());
        System.err.println("Soma: " + soma);
        
        //Compara com o valor obtido pelo calculo funcional
        assertEquals(contaAtualizada.getCValor(), soma, 0.0001);
    }

    /*
    Verifica se a taxa de serviço foi aplicada corretamente sobre o valor total
    da conta.    
    */
    @Test
    public void testCalculoTaxaServicoIgual2() {
        System.err.println("\ntestCalculoTaxaServicoIgual2:");
        
        CalculoValores calculoValores = new TaxaServicoIgual();
        
        //Obtem o valorTotal sem a taxa de serviço
        double valorTotal = calculoValores.calculoValorTotal(conta).getCValor();
        System.err.println("Valor total sem taxa: " + valorTotal);
        
        //Aplica a taxa de serviço
        valorTotal += (valorTotal * conta.getCTaxaServico()) / 100;
        System.err.println("Valor total com taxa:" + valorTotal);  
        
        double valorTotalCalculoFuncional = calculoValores.atualizarValores(conta).getCValor();
        System.err.println("Valor total cálculo funcional: " + valorTotalCalculoFuncional);
        
        assertEquals(valorTotal, valorTotalCalculoFuncional, 0.0001);
    }
    
}
