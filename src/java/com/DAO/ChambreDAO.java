/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Chambre;
import java.util.ArrayList;
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
public class ChambreDAO implements ChambreDAOLocal {
    @PersistenceContext
    private EntityManager em;        
    @Override
    public List<Chambre> getAllChambre() {
        return em.createNamedQuery("Chambre.findAll").getResultList();
    }  
    @Override
    public void deleteChambre(int idchambre) {
        em.remove(RechercheID(idchambre));
    }
    @Override
    public Chambre RechercheID(int idchambre) {
        return em.find(Chambre.class, idchambre);
    }
    
    @Override
    public void editChambre(Chambre chambre) {
        em.merge(chambre);
    }

    @Override
    public void addChambre(Chambre chambre) {
        em.persist(chambre);
    }      
}
