/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "locataire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locataire.findAll", query = "SELECT l FROM Locataire l"),
    @NamedQuery(name = "Locataire.findByIdlocataire", query = "SELECT l FROM Locataire l WHERE l.idlocataire = :idlocataire"),
    @NamedQuery(name = "Locataire.findByNomlocataire", query = "SELECT l FROM Locataire l WHERE l.nomlocataire = :nomlocataire"),
    @NamedQuery(name = "Locataire.findByPrenomlocataire", query = "SELECT l FROM Locataire l WHERE l.prenomlocataire = :prenomlocataire"),
    @NamedQuery(name = "Locataire.findByProfessionlocataire", query = "SELECT l FROM Locataire l WHERE l.professionlocataire = :professionlocataire"),
    @NamedQuery(name = "Locataire.findByDatearrivee", query = "SELECT l FROM Locataire l WHERE l.datearrivee = :datearrivee"),
    @NamedQuery(name = "Locataire.findByNbjrslocataire", query = "SELECT l FROM Locataire l WHERE l.nbjrslocataire = :nbjrslocataire"),
    @NamedQuery(name = "Locataire.findByAdresselocataire", query = "SELECT l FROM Locataire l WHERE l.adresselocataire = :adresselocataire"),
    @NamedQuery(name = "Locataire.findByPayement", query = "SELECT l FROM Locataire l WHERE l.payement = :payement"),
    @NamedQuery(name = "Locataire.findByAvance", query = "SELECT l FROM Locataire l WHERE l.avance = :avance"),
    @NamedQuery(name = "Locataire.findByMois", query = "SELECT l FROM Locataire l WHERE l.mois = :mois"),
    @NamedQuery(name = "Locataire.findByAnnee", query = "SELECT l FROM Locataire l WHERE l.annee = :annee")})
public class Locataire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLOCATAIRE")
    private Integer idlocataire;
    @Size(max = 255)
    @Column(name = "NOMLOCATAIRE")
    private String nomlocataire;
    @Size(max = 255)
    @Column(name = "PRENOMLOCATAIRE")
    private String prenomlocataire;
    @Size(max = 255)
    @Column(name = "PROFESSIONLOCATAIRE")
    private String professionlocataire;
    @Size(max = 32)
    @Column(name = "DATEARRIVEE")
    private String datearrivee;
    @Size(max = 32)
    @Column(name = "NBJRSLOCATAIRE")
    private String nbjrslocataire;
    @Size(max = 255)
    @Column(name = "ADRESSELOCATAIRE")
    private String adresselocataire;
    @Size(max = 32)
    @Column(name = "payement")
    private String payement;
    @Size(max = 32)
    @Column(name = "avance")
    private String avance;
    @Size(max = 32)
    @Column(name = "mois")
    private String mois;
    @Size(max = 32)
    @Column(name = "annee")
    private String annee;
    @JoinColumn(name = "IDCHAMBRE", referencedColumnName = "IDCHAMBRE")
    @ManyToOne(optional = false)
    private Chambre idchambre;

    public Locataire() {
    }

    public Locataire(String nomlocataire, String prenomlocataire, String professionlocataire, String datearrivee, String nbjrslocataire, String adresselocataire, String payement, String avance, String mois, String annee, Chambre idchambre) {
        this.nomlocataire = nomlocataire;
        this.prenomlocataire = prenomlocataire;
        this.professionlocataire = professionlocataire;
        this.datearrivee = datearrivee;
        this.nbjrslocataire = nbjrslocataire;
        this.adresselocataire = adresselocataire;
        this.payement = payement;
        this.avance = avance;
        this.mois = mois;
        this.annee = annee;
        this.idchambre = idchambre;
    }

    public Locataire(Integer idlocataire) {
        this.idlocataire = idlocataire;
    }

    public Integer getIdlocataire() {
        return idlocataire;
    }

    public void setIdlocataire(Integer idlocataire) {
        this.idlocataire = idlocataire;
    }

    public String getNomlocataire() {
        return nomlocataire;
    }

    public void setNomlocataire(String nomlocataire) {
        this.nomlocataire = nomlocataire;
    }

    public String getPrenomlocataire() {
        return prenomlocataire;
    }

    public void setPrenomlocataire(String prenomlocataire) {
        this.prenomlocataire = prenomlocataire;
    }

    public String getProfessionlocataire() {
        return professionlocataire;
    }

    public void setProfessionlocataire(String professionlocataire) {
        this.professionlocataire = professionlocataire;
    }

    public String getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(String datearrivee) {
        this.datearrivee = datearrivee;
    }

    public String getNbjrslocataire() {
        return nbjrslocataire;
    }

    public void setNbjrslocataire(String nbjrslocataire) {
        this.nbjrslocataire = nbjrslocataire;
    }

    public String getAdresselocataire() {
        return adresselocataire;
    }

    public void setAdresselocataire(String adresselocataire) {
        this.adresselocataire = adresselocataire;
    }

    public String getPayement() {
        return payement;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

    public String getAvance() {
        return avance;
    }

    public void setAvance(String avance) {
        this.avance = avance;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Chambre getIdchambre() {
        return idchambre;
    }

    public void setIdchambre(Chambre idchambre) {
        this.idchambre = idchambre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocataire != null ? idlocataire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locataire)) {
            return false;
        }
        Locataire other = (Locataire) object;
        if ((this.idlocataire == null && other.idlocataire != null) || (this.idlocataire != null && !this.idlocataire.equals(other.idlocataire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Locataire[ idlocataire=" + idlocataire + " ]";
    }
}
