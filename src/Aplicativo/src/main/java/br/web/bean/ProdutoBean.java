/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web.bean;


import br.jpa.controller.ProdutoJpaController;
import br.jpa.controller.UsuarioJpaController;
import br.jpa.controller.exceptions.NonexistentEntityException;
import br.jpa.entity.Produto;
import br.jpa.entity.Usuario;
import br.web.utils.SessionContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matheus Mollon
 */
@ManagedBean
@RequestScoped
public class ProdutoBean {

    private int id;
    private String produto;
    private double preco;
    private String[] selecionados;
    private List<Usuario> users_por_produto;

    public ProdutoBean() {
        this.selecionados = new String[ProdutoJpaController.getInstance().findProdutoEntities().size()];
        this.users_por_produto = new ArrayList<>();

    }

    public String[] getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(String[] selecionados) {
        this.selecionados = selecionados;
    }

    public void adicionar() {
        ProdutoJpaController pjc = ProdutoJpaController.getInstance();
        List<Usuario> users = new ArrayList<Usuario>();

        for (int i = 0; i < selecionados.length; i++) {
            System.out.println(UsuarioJpaController.getInstance().findUsuario(selecionados[i]).toString());
            users.add(UsuarioJpaController.getInstance().findUsuario(selecionados[i]));
        }

        Produto p = new Produto();
        p.setPId(pjc.findProdutoEntities().size() + 1);
        p.setPNome(produto);
        p.setPValor(preco);
        for (Usuario u : users) {
            System.out.println(u.toString());
        }
        p.setUsuarioCollection(users);
        pjc.create(p);
        System.out.println("done");

    }

    public void remove(Produto produto) {
        try {
            ProdutoJpaController.getInstance().destroy(produto.getPId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clean() {
        this.setId(-1);
        this.produto = "";
        this.preco = 0.0;
    }

  

    public void loadData(Produto prod) {
        int produto_id = prod.getPId();
        SessionContext.getInstance().setSessionAttribute("produto_edit",produto_id);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/editar_produto.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("loading..." + prod.toString());

    }

    public List<Produto> getAllProdutos() {
        return ProdutoJpaController.getInstance().findProdutoEntities();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void getUsers_por_produto(int id) {
            ProdutoJpaController pjc = ProdutoJpaController.getInstance();
            this.users_por_produto = pjc.getAllUsersFromProduct(id);
            
    }

    public List<Usuario> getUsers_por_produto() {
        return users_por_produto;
    }
        

    public void limparDetalhes() {
        users_por_produto = new ArrayList<>();
    }


}
