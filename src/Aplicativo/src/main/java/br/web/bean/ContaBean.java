/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web.bean;

import br.jpa.controller.ContaJpaController;
import br.jpa.entity.Conta;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Felipe
 */
@Named(value = "contaBean")
@RequestScoped
public class ContaBean {
    
    private Conta novaConta;
    /**
     * Creates a new instance of ContaBean
     */
    public ContaBean() {
        this.novaConta = new Conta();
    }
    
    public void criarNovaConta(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplicativoPU");
    }
    
}
