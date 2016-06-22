/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web.bean;

import br.jpa.controller.UsuarioJpaController;
import br.jpa.entity.Usuario;
import br.web.utils.SessionContext;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hideki
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuario;
    private String confirmaSenha;
    private List<Usuario> usuarios;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        this.usuario = new Usuario();
        this.usuarios = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void cadastrarUsuario() {
        if (usuario.getUSenha().equals(confirmaSenha)) {
            try {
                UsuarioJpaController.getInstance().create(usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/login.xhtml");
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no cadastro!", "Falha no cadastro!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(ex.toString());
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem!", "Falha no cadastro!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Usuario getUsuarioSession() {
        return UsuarioJpaController.getInstance().findUsuario(SessionContext.getInstance().getSessionAttribute("uNome").toString());
    }

    public void atualizarUsuario() {
        Usuario usuarioAtualizado = this.getUsuarioSession();
        usuarioAtualizado.setUCelular(this.usuario.getUCelular());

        try {
            UsuarioJpaController.getInstance().edit(usuarioAtualizado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/sistema.xhtml");
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na atualização dos dados!", "Falha na atualização dos dados!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println(ex.toString());
        }
    }

    public void excluirUsuario() {
        Usuario usuarioExcluido = this.getUsuarioSession();

        if (this.usuario.getUSenha().equals(usuarioExcluido.getUSenha())) {
            try {
                UsuarioJpaController.getInstance().destroy(usuarioExcluido.getUNome());
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Aplicativo/faces/login.xhtml");
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na exclusão do usuário!", "Falha na exclusão do usuário!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(ex.toString());
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!", "Senha incorreta!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Usuario> searchResults() {
        return UsuarioJpaController.getInstance().findUsuarioLike(this.usuario.getUNome());
    }
    
    public List<Usuario> getAllUsers(){
        return UsuarioJpaController.getInstance().findUsuarioEntities();
    }

    public String searchNumbers() {
        return "Total de resultados: " + UsuarioJpaController.getInstance().findUsuarioLike(this.usuario.getUNome()).size();
    }

}
