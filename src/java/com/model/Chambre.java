/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djazz jah
 */
@Entity
@Table(name = "chambre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chambre.findAll", query = "SELECT c FROM Chambre c"),
    @NamedQuery(name = "Chambre.findByIdchambre", query = "SELECT c FROM Chambre c WHERE c.idchambre = :idchambre"),
    @NamedQuery(name = "Chambre.findBySurfacechambre", query = "SELECT c FROM Chambre c WHERE c.surfacechambre = :surfacechambre"),
    @NamedQuery(name = "Chambre.findByQualitechambre", query = "SELECT c FROM Chambre c WHERE c.qualitechambre = :qualitechambre"),
    @NamedQuery(name = "Chambre.findByLoyerchambre", query = "SELECT c FROM Chambre c WHERE c.loyerchambre = :loyerchambre"),
    @NamedQuery(name = "Chambre.findByDispochambre", query = "SELECT c FROM Chambre c WHERE c.dispochambre = :dispochambre")})
public class Chambre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCHAMBRE")
    private Integer idchambre;
    @Size(max = 255)
    @Column(name = "SURFACECHAMBRE")
    private String surfacechambre;
    @Size(max = 128)
    @Column(name = "QUALITECHAMBRE")
    private String qualitechambre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LOYERCHAMBRE")
    private BigDecimal loyerchambre;
    @Size(max = 255)
    @Column(name = "DISPOCHAMBRE")
    private String dispochambre;

    public Chambre() {
    }

    public Chambre(Integer idchambre) {
        this.idchambre = idchambre;
    }

    public Integer getIdchambre() {
        return idchambre;
    }

    public void setIdchambre(Integer idchambre) {
        this.idchambre = idchambre;
    }

    public String getSurfacechambre() {
        return surfacechambre;
    }

    public void setSurfacechambre(String surfacechambre) {
        this.surfacechambre = surfacechambre;
    }

    public String getQualitechambre() {
        return qualitechambre;
    }

    public void setQualitechambre(String qualitechambre) {
        this.qualitechambre = qualitechambre;
    }

    public BigDecimal getLoyerchambre() {
        return loyerchambre;
    }

    public void setLoyerchambre(BigDecimal loyerchambre) {
        this.loyerchambre = loyerchambre;
    }

    public String getDispochambre() {
        return dispochambre;
    }

    public void setDispochambre(String dispochambre) {
        this.dispochambre = dispochambre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchambre != null ? idchambre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chambre)) {
            return false;
        }
        Chambre other = (Chambre) object;
        if ((this.idchambre == null && other.idchambre != null) || (this.idchambre != null && !this.idchambre.equals(other.idchambre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Chambre[ idchambre=" + idchambre + " ]";
    }
    
}
