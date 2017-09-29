/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Djazz jah
 */
@Local
public interface UtilisateurDAOLocal {

    List<Utilisateur> ListeUser();

    void SuppressionUser(int id);

    Utilisateur RechercherId(int id);

    void ModificationUser(Utilisateur user);

    void addUser(Utilisateur user);       

    List<Utilisateur> verif(String login, String password);
}
