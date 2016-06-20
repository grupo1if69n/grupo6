/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByIdconta", query = "SELECT c FROM Conta c WHERE c.idconta = :idconta"),
    @NamedQuery(name = "Conta.findByNomeconta", query = "SELECT c FROM Conta c WHERE c.nomeconta = :nomeconta"),
    @NamedQuery(name = "Conta.findByValortotal", query = "SELECT c FROM Conta c WHERE c.valortotal = :valortotal")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta")
    private Integer idconta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomeconta")
    private String nomeconta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valortotal")
    private double valortotal;

    public Conta() {
    }

    public Conta(Integer idconta) {
        this.idconta = idconta;
    }

    public Conta(Integer idconta, String nomeconta, double valortotal) {
        this.idconta = idconta;
        this.nomeconta = nomeconta;
        this.valortotal = valortotal;
    }

    public Integer getIdconta() {
        return idconta;
    }

    public void setIdconta(Integer idconta) {
        this.idconta = idconta;
    }

    public String getNomeconta() {
        return nomeconta;
    }

    public void setNomeconta(String nomeconta) {
        this.nomeconta = nomeconta;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconta != null ? idconta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.idconta == null && other.idconta != null) || (this.idconta != null && !this.idconta.equals(other.idconta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.jpa.entity.Conta[ idconta=" + idconta + " ]";
    }
    
}
