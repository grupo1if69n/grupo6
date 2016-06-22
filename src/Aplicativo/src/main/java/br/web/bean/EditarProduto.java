/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web.bean;

import br.jpa.controller.CacheProduto;
import br.jpa.controller.ProdutoJpaController;
import br.jpa.controller.UsuarioJpaController;
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
public class EditarProduto {

    private Produto produto;
    private List<Usuario> prod_users;

    public EditarProduto() {
        this.prod_users = new ArrayList<>();
        this.produto = new Produto();

    }

    public Produto getProduto() {

        return produto;
    }

    public void setProduto(Produto produto) {

        this.produto = produto;

    }

    public List<Usuario> getProd_users() {
        CacheProduto cache = CacheProduto.getInstance();
        cache.setUsers(recuperarReferencia());
        this.prod_users = cache.getUsers();
        return prod_users;
    }

    public void setProd_users(List<Usuario> prod_users) {
        this.prod_users = prod_users;
    }

    public void edit() {
        System.out.println("EDIT");
        Produto p = new Produto();
        p.setIdproduto(this.produto.getIdproduto());
        p.setNomeproduto(this.produto.getNomeproduto());
        p.setPrecoproduto(this.produto.getPrecoproduto());
        p.setUsuarioCollection(CacheProduto.getInstance().getUsers());
        System.out.println(p.toString());
        for (Usuario u : CacheProduto.getInstance().getUsers()) {
            System.out.println(u.toString());
        }
        try {
            ProdutoJpaController.getInstance().edit(p);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CacheProduto.getInstance().liberar();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/produto.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private int recuperarReferencia() {
        String id_name = SessionContext.getInstance().getSessionAttribute("produto_edit").toString();
        return Integer.valueOf(id_name);
    }

    public void obterDados() {
        try {
            ProdutoJpaController pjc = ProdutoJpaController.getInstance();
            String id_name = SessionContext.getInstance().getSessionAttribute("produto_edit").toString();
            int id = Integer.valueOf(id_name);
            this.produto = pjc.findProduto(id);
        } catch (NullPointerException e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/produto.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removerUsuario(Usuario u) {
        CacheProduto.getInstance().removeItem(u);
    }

    public void cancelar() {
        try {
            CacheProduto.getInstance().liberar();
            this.produto = new Produto();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/produto.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarUsuario(Usuario u) {
        CacheProduto.getInstance().addItem(u);
    }

    public List<Usuario> adicaoUsuario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplicativoPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        List<Usuario> u_aux = ujc.findUsuarioEntities();
        List<Usuario> cache_aux = CacheProduto.getInstance().getUsers();
        List<Usuario> resposta = new ArrayList<>();
        for (int i = 0; i < u_aux.size(); i++) {
            if (!cache_aux.contains(u_aux.get(i))) {
                resposta.add(u_aux.get(i));
            }

        }
        return resposta;
    }

}