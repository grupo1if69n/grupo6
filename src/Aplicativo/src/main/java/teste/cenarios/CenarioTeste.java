/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.cenarios;

import br.jpa.entity.Conta;
import br.jpa.entity.Produto;
import br.jpa.entity.Usuario;
import br.jpa.entity.UsuarioConta;
import br.jpa.entity.UsuarioContaPK;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hideki
 */
public class CenarioTeste {

    public static Conta getCenarioTeste1() {
        //Criar o objeto de Conta
        Conta conta = new Conta(1, "Costela", 0, "eduardo", 10, true);

        //Criar os objetos de Usuario
        Usuario u1 = new Usuario("eduardo", "1234", "01 23456789");
        Usuario u2 = new Usuario("felipe", "1234", "01 23456789");
        Usuario u3 = new Usuario("matheus", "1234", "01 23456789");

        //Criar os objetos de Produto     
        Produto p1 = new Produto(1, "Batata c/ queijo e bacon", 24.50);
        Produto p2 = new Produto(2, "Isca de frango", 25.50);
        Produto p3 = new Produto(3, "Torre de cerveja", 37.00);
        Produto p4 = new Produto(4, "Coca-cola 600ml", 7.50);

        //Criar os objetos de UsuarioContaPK
        UsuarioContaPK ucpk1 = new UsuarioContaPK(u1.getUNome(), conta.getCId());
        UsuarioContaPK ucpk2 = new UsuarioContaPK(u2.getUNome(), conta.getCId());
        UsuarioContaPK ucpk3 = new UsuarioContaPK(u3.getUNome(), conta.getCId());

        //Criar os objetos de UsuarioConta
        UsuarioConta uc1 = new UsuarioConta(ucpk1, 0);
        UsuarioConta uc2 = new UsuarioConta(ucpk2, 0);
        UsuarioConta uc3 = new UsuarioConta(ucpk3, 0);

        //Criar os objetos de Collection<UsuarioConta> para cada Usuario
        Collection<UsuarioConta> cuc1 = new ArrayList<>();
        cuc1.add(uc1);
        Collection<UsuarioConta> cuc2 = new ArrayList<>();
        cuc2.add(uc2);
        Collection<UsuarioConta> cuc3 = new ArrayList<>();
        cuc3.add(uc3);

        //Adicionar os objetos de Collection<UsuarioConta> em cada Usuario
        u1.setUsuarioContaCollection(cuc1);
        u2.setUsuarioContaCollection(cuc2);
        u3.setUsuarioContaCollection(cuc3);

        //Criar o objeto de Collection<UsuarioConta> para Conta
        Collection<UsuarioConta> cuc4 = new ArrayList<>();
        cuc4.add(uc1);
        cuc4.add(uc2);
        cuc4.add(uc3);

        //Adicionar o objeto de Collection<UsuarioConta> em Conta
        conta.setUsuarioContaCollection(cuc4);

        //Criar os objetos Collection<Usuario> para cada Produto
        Collection<Usuario> cu1 = new ArrayList<>();
        cu1.add(u1);
        cu1.add(u2);
        cu1.add(u3);
        Collection<Usuario> cu2 = new ArrayList<>();
        cu2.add(u1);
        cu2.add(u2);
        cu2.add(u3);
        Collection<Usuario> cu3 = new ArrayList<>();
        cu3.add(u2);
        cu3.add(u3);
        Collection<Usuario> cu4 = new ArrayList<>();
        cu4.add(u1);

        //Adicionar os objetos de Collection<Usuario> em cada Produto  
        p1.setUsuarioCollection(cu1);
        p2.setUsuarioCollection(cu2);
        p3.setUsuarioCollection(cu3);
        p4.setUsuarioCollection(cu4);

        //Criar o objeto Collection<Produto> para a conta
        Collection<Produto> produtos = new ArrayList<>();
        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
        produtos.add(p4);

        //Adicionar o objeto de Collection<Produto> em Conta
        conta.setProdutoCollection(produtos);

        return conta;
    }

    public static Collection<Conta> getCenarioTeste2() {
        Collection<Conta> contas = new ArrayList<>();

        //Criar os objetos de Conta
        Conta c1 = new Conta(1, "Costela", 98.50, "eduardo", 5, false);
        Conta c2 = new Conta(2, "Costela", 115.50, "felipe", 5, false);
        Conta c3 = new Conta(3, "Costela", 89.50, "matheus", 5, false);

        //Preencher o Collection<Conta>
        contas = new ArrayList<>();
        contas.add(c1);
        contas.add(c2);
        contas.add(c3);

        return contas;
    }

    public static Collection<UsuarioConta> getCenarioTeste3() {
        Collection<UsuarioConta> usuarioContas = new ArrayList<>();

        //Criar os objetos de UsuarioConta
        UsuarioContaPK ucpk1 = new UsuarioContaPK("eduardo", 1);
        UsuarioConta uc1 = new UsuarioConta(ucpk1, 34.50);
        UsuarioContaPK ucpk2 = new UsuarioContaPK("eduardo", 2);
        UsuarioConta uc2 = new UsuarioConta(ucpk2, 22.50);
        UsuarioContaPK ucpk3 = new UsuarioContaPK("eduardo", 3);
        UsuarioConta uc3 = new UsuarioConta(ucpk3, 25.50);

        //Preencher o Collection<UsuarioConta>
        usuarioContas = new ArrayList<>();
        usuarioContas.add(uc1);
        usuarioContas.add(uc2);
        usuarioContas.add(uc3);

        return usuarioContas;
    }

    public static Conta getCenarioTeste4() {

        List<Usuario> usuarios = new ArrayList<>();
        List<UsuarioConta> usuarioConta = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        Conta conta = new Conta();

        //Criando 3 usuários 
        Usuario u1 = new Usuario("User1", "1", "43 98684323");
        Usuario u2 = new Usuario("User2", "1", "43 98684323");
        Usuario u3 = new Usuario("User3", "1", "43 98684323");
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);

        //Criando uma conta
        conta = new Conta(100, "Costela", 0.0, u1.getUNome(), 10, true);

        //adicionando usuários a conta
        UsuarioConta uc1 = new UsuarioConta(u1.getUNome(), 100);
        uc1.setConta(conta);
        uc1.setUsuario(u1);
        uc1.setUCValor(0.0);
        usuarioConta.add(uc1);

        UsuarioConta uc2 = new UsuarioConta(u2.getUNome(), 100);
        uc2.setConta(conta);
        uc2.setUsuario(u2);
        uc2.setUCValor(0.0);
        usuarioConta.add(uc2);

        UsuarioConta uc3 = new UsuarioConta(u3.getUNome(), 100);
        uc3.setConta(conta);
        uc3.setUsuario(u3);
        uc3.setUCValor(0.0);
        usuarioConta.add(uc3);

        //atribuindo UsuarioConta ao usuario
        List<UsuarioConta> uc1t = new ArrayList<>();
        uc1t.add(uc1);
        u1.setUsuarioContaCollection(uc1t);
        List<UsuarioConta> uc2t = new ArrayList<>();
        uc2t.add(uc2);
        u2.setUsuarioContaCollection(uc2t);
        List<UsuarioConta> uc3t = new ArrayList<>();
        uc3t.add(uc3);
        u3.setUsuarioContaCollection(uc3t);

        //Criando Produtos e atribuindo os usuários
        Produto p1 = new Produto(500, "Batata Frita", 30.00);
        List<Usuario> usersP1 = new ArrayList<>();
        usersP1.add(usuarios.get(0));
        usersP1.add(usuarios.get(2));
        p1.setUsuarioCollection(usersP1);
        p1.setCId(conta);
        produtos.add(p1);

        Produto p2 = new Produto(501, "Frango Empanado", 35.00);
        List<Usuario> usersP2 = new ArrayList<>();
        usersP2.add(usuarios.get(1));
        usersP2.add(usuarios.get(2));
        p2.setCId(conta);
        p2.setUsuarioCollection(usersP2);
        produtos.add(p2);

        Produto p3 = new Produto(502, "Torre de Chopp", 40.00);
        p3.setUsuarioCollection(usuarios);
        p3.setCId(conta);
        produtos.add(p3);

        //Atribuindo os produtos a conta
        conta.setProdutoCollection(produtos);
        conta.setUsuarioContaCollection(usuarioConta);
        return conta;
    }

}
