/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Chambre;
import com.model.Locataire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Djazz jah
 */
@Local
public interface LocataireDAOLocal {

    List<Locataire> getAllLocataire();

    Locataire RechercheID(int idlocataire);

    void deleteLocataire(int idlocataire);

    void modiifacation(Chambre chambre);

    List<Chambre> ListChambre();

    void addLocataire(Locataire locataire);

    Chambre findChambre(int idchambre);

    List<Chambre> ListeDisop(int idchambre);

    void editLocataire(Locataire locataire);

    List<Locataire> EntreDate(String date1, String date2);

    List<Locataire> EtatPaie(int idlocataire);
//    List<Locataire> EtatPaies(String ids);

    List<Locataire> comptes(String mois, String annee);
}
