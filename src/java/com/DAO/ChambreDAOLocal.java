/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Chambre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Djazz jah
 */
@Local
public interface ChambreDAOLocal {

    List<Chambre> getAllChambre();       

    void deleteChambre(int idchambre);

    Chambre RechercheID(int idchambre);

    void editChambre(Chambre chambre);

    void addChambre(Chambre chambre);   
    
}
