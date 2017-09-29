/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Chambre;
import com.model.Locataire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Djazz jah
 */
@Stateless
public class LocataireDAO implements LocataireDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Locataire> getAllLocataire() {
        return em.createNamedQuery("Locataire.findAll").getResultList();
    }

    @Override
    public Locataire RechercheID(int idlocataire) {
        return em.find(Locataire.class, idlocataire);
    }

    @Override
    public void deleteLocataire(int idlocataire) {
        em.remove(RechercheID(idlocataire));
    }

    @Override
    public void modiifacation(Chambre chambre) {
        em.merge(chambre);
    }

    @Override
    public List<Chambre> ListChambre() {
        Query query = em.createNamedQuery("Chambre.findByDispochambre");
        query.setParameter("dispochambre", "DISPONIBLE");
        return query.getResultList();
    }

    @Override
    public void addLocataire(Locataire locataire) {
        em.persist(locataire);
    }

    @Override
    public Chambre findChambre(int idchambre) {
        return em.find(Chambre.class, idchambre);
    }

    @Override
    public List<Chambre> ListeDisop(int idchambre) {
        Query query = em.createQuery("SELECT c FROM Chambre c where c.dispochambre = :dispochambre OR c.idchambre = :idchambre  group by c.idchambre");
        query.setParameter("dispochambre", "DISPONIBLE");
        query.setParameter("idchambre", idchambre);
        return query.getResultList();
    }

    @Override
    public void editLocataire(Locataire locataire) {
        em.merge(locataire);
    }

    @Override
    public List<Locataire> EntreDate(String date1, String date2) {
        Query query = em.createQuery("SELECT l FROM Locataire l WHERE l.datearrivee BETWEEN :date1 AND :date2");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

    @Override
    public List<Locataire> EtatPaie(int idlocataire) {
        Query query = em.createQuery("SELECT l FROM Locataire l WHERE l.idlocataire = :idlocataire ");
        query.setParameter("idlocataire", idlocataire);
//        query.setParameter("idlocataire", (Integer.parseInt(idlocataire)));           
        return query.getResultList();
    }

    @Override
    public List<Locataire> comptes(String mois, String annee) {
        Query query = em.createQuery("SELECT count(l) as nbre  FROM Locataire l WHERE l.mois = :mois AND l.annee = :annee");
        query.setParameter("mois", mois);
        query.setParameter("annee", annee);
        return query.getResultList();
    }
}
