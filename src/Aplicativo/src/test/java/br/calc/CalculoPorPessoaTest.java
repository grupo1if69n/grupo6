/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calc;

import br.jpa.entity.Conta;
import br.jpa.entity.UsuarioConta;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import teste.cenarios.CenarioTeste;

/**
 *
 * @author grupo1if69n
 */
public class CalculoPorPessoaTest {

    public CalculoPorPessoaTest() {
    }

    public Conta getContaAtualizada() {
        Conta c = CenarioTeste.getCenarioTeste4();
        CalculoValores cv = new TaxaServicoPorConsumo();
        Conta cAtualizada = cv.atualizarValores(c);
        return cAtualizada;
    }

    @Test
    public void calculoPorPessoa1() {
        Conta c = getContaAtualizada();
        List<UsuarioConta> ucs = (List<UsuarioConta>) c.getUsuarioContaCollection();
        UsuarioConta uc = null;
        for (UsuarioConta uc1 : ucs) {
            if (uc1.getUsuario().getUNome().equals("User1")) {
                uc = uc1;
            }
        }
        assertEquals(31.16, uc.getUCValor(), 0.01);

    }

    @Test
    public void calculoPorPessoa2() {
        Conta c = getContaAtualizada();
        List<UsuarioConta> ucs = (List<UsuarioConta>) c.getUsuarioContaCollection();
        UsuarioConta uc = null;
        for (UsuarioConta uc1 : ucs) {
            if (uc1.getUsuario().getUNome().equals("User2")) {
                uc = uc1;
            }
        }
        assertEquals(33.91, uc.getUCValor(), 0.01);

    }

    @Test
    public void calculoPorPessoa3() {
        Conta c = getContaAtualizada();
        List<UsuarioConta> ucs = (List<UsuarioConta>) c.getUsuarioContaCollection();
        UsuarioConta uc = null;
        for (UsuarioConta uc1 : ucs) {
            if (uc1.getUsuario().getUNome().equals("User3")) {
                uc = uc1;
            }
        }
        assertEquals(50.42, uc.getUCValor(), 0.01);

    }

    @Test
    public void CalculoPorPessoaComparandoAsPartesComValorTotal() {
        Conta c = getContaAtualizada();
        List<UsuarioConta> ucs = (List<UsuarioConta>) c.getUsuarioContaCollection();
        double valorSomadoUsuario = 0.0;
        for (UsuarioConta uc : ucs) {
            valorSomadoUsuario += uc.getUCValor();
        }
        assertEquals(c.getCValor(), valorSomadoUsuario, 0.01);
    }
}
