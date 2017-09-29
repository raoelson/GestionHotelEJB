/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Utilisateur;
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
public class UtilisateurDAO implements UtilisateurDAOLocal {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Utilisateur> ListeUser() {
        return em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
    @Override
    public void SuppressionUser(int id) {
        em.remove(RechercherId(id));
    }

    @Override
    public Utilisateur RechercherId(int id) {
        return em.find(Utilisateur.class, id);
    }

    @Override
    public void ModificationUser(Utilisateur user) {
        em.merge(user);
    }

    @Override
    public void addUser(Utilisateur user) {
        em.persist(user);
    } 

    @Override
    public List<Utilisateur> verif(String login, String password) {
        Query query = em.createQuery("SELECT u FROM Utilisateur u where u.login = :login and u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return query.getResultList();
    }
    
}
