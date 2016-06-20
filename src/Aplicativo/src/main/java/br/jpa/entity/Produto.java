/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jpa.entity;

import br.jpa.controller.ProdutoJpaController;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Matheus Mollon
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdproduto", query = "SELECT p FROM Produto p WHERE p.idproduto = :idproduto"),
    @NamedQuery(name = "Produto.findByNomeproduto", query = "SELECT p FROM Produto p WHERE p.nomeproduto = :nomeproduto"),
    @NamedQuery(name = "Produto.findByPrecoproduto", query = "SELECT p FROM Produto p WHERE p.precoproduto = :precoproduto")})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "getAllUsersFromProduct",
            query = "select * from usuario u, usuario_produto pu where (u.u_nome = pu.u_nome) and pu.produto=?;",
            resultClass = Usuario.class
    )
      
})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto")
    private Integer idproduto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomeproduto")
    private String nomeproduto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precoproduto")
    private double precoproduto;
    @ManyToMany(mappedBy = "produtoCollection")
    private List<Usuario> usuarioCollection;

    public Produto() {
    }

    public Produto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Produto(Integer idproduto, String nomeproduto, double precoproduto) {
        this.idproduto = idproduto;
        this.nomeproduto = nomeproduto;
        this.precoproduto = precoproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public double getPrecoproduto() {
        return precoproduto;
    }

    public void setPrecoproduto(double precoproduto) {
        this.precoproduto = precoproduto;
    }

    public int getQtdUsersProduct(int id) {

        return ProdutoJpaController.getInstance().getAllUsersFromProduct(id).size();
    }

    public List<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(List<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "idproduto=" + idproduto + ", nomeproduto=" + nomeproduto + ", precoproduto=" + precoproduto + '}';
    }

   

}
