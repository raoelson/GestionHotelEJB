/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.ChambreDAO;
import com.DAO.ChambreDAOLocal;
import com.DAO.LocataireDAOLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.Chambre;
import com.model.Locataire;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Djazz jah
 */
public class LocataireController extends HttpServlet {

    @EJB
    private LocataireDAOLocal dao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("actions");
        String forwds = "";
        int id;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        Locataire locataireclasse = new Locataire();
        Locataire locataireclasses = new Locataire();
        Chambre chambre = new Chambre();
        List<Chambre> chambreListe = new ArrayList<Chambre>();
        List<Locataire> locataire = new ArrayList<Locataire>();
        try {
            if (action.equalsIgnoreCase("wlcm")) {
                request.setAttribute("titles", "Locataire");
                request.setAttribute("connecte", "Locataire");
                forwds = "/Locataire.jsp";
            } else if (action.equalsIgnoreCase("listeslocataire")) {
                String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                if(date1.equalsIgnoreCase("") && date2.equalsIgnoreCase("")){
                    locataire = dao.getAllLocataire();
                }
                else{
                    locataire = dao.EntreDate(date1, date2);
                }
                JsonElement jsonelemnt = gson.toJsonTree(locataire);
                myObj.add("listeslocataire", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("delete")) {
                id = Integer.parseInt(request.getParameter("id"));
                locataireclasse = dao.RechercheID(id);
                chambre.setIdchambre(locataireclasse.getIdchambre().getIdchambre());
                chambre.setSurfacechambre(locataireclasse.getIdchambre().getSurfacechambre());
                chambre.setQualitechambre(locataireclasse.getIdchambre().getQualitechambre());
                chambre.setLoyerchambre(locataireclasse.getIdchambre().getLoyerchambre());
                chambre.setDispochambre("DISPONIBLE");
                dao.modiifacation(chambre);
                dao.deleteLocataire(id);
                JsonElement jsonelemnt = gson.toJsonTree(id);
                myObj.add("id", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("recherches")) {
                id = Integer.parseInt(request.getParameter("id"));
                locataireclasse = dao.RechercheID(id);
                JsonElement jsonelemnt = gson.toJsonTree(locataireclasse);
                myObj.add("findlocataire", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("chargmnt")) {
                chambreListe = dao.ListChambre();
                JsonElement jsonelemnt = gson.toJsonTree(chambreListe);
                myObj.add("listeschambres", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("create")) {
                id = Integer.parseInt(request.getParameter("id"));
                int numchambre = Integer.parseInt(request.getParameter("numchambre"));
                Chambre ch = new Chambre(numchambre);
                String nom = request.getParameter("nomlocataire");
                String prenom = request.getParameter("prenom");
                String prof = request.getParameter("prof");
                String datepicker = request.getParameter("datepicker");
                String nbr = request.getParameter("nbr");
                String adresse = request.getParameter("adresse");
                String payement = request.getParameter("payement");
                String avance = request.getParameter("avance");
                String mois = datepicker.substring(5, 7);
                String annee = datepicker.substring(0, 4);

                locataireclasse.setNomlocataire(nom);
                locataireclasse.setPrenomlocataire(prenom);
                locataireclasse.setProfessionlocataire(prof);
                locataireclasse.setDatearrivee(datepicker);
                locataireclasse.setNbjrslocataire(nbr);
                locataireclasse.setAdresselocataire(adresse);
                locataireclasse.setPayement(payement);
                locataireclasse.setAvance(avance);
                locataireclasse.setMois(mois);
                locataireclasse.setAnnee(annee);
                locataireclasse.setIdchambre(ch);
                locataireclasse.setIdlocataire(id);

                if (id == 0) {
                    chambre = ptfunction(chambre, numchambre, "NON");
                    dao.addLocataire(locataireclasse);
                    dao.modiifacation(chambre);
                } else if (id != 0) {
                    locataireclasses = dao.RechercheID(id);
                    if ((locataireclasses.getIdchambre().getIdchambre()) == numchambre) {
                        dao.editLocataire(locataireclasse);
                    } else {
                        chambre = ptfunction(chambre, numchambre, "NON");
                        dao.modiifacation(chambre);
                        dao.editLocataire(locataireclasse);
                        chambre = ptfunction(chambre, (locataireclasses.getIdchambre().getIdchambre()), "DISPONIBLE");
                        dao.modiifacation(chambre);
                    }
                }
                JsonElement jsonelement = gson.toJsonTree(locataireclasse);
                if (locataireclasse == null) {
                    myObj.addProperty("success", false);
                } else {
                    myObj.addProperty("success", true);
                }
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("recherches")) {
                id = Integer.parseInt(request.getParameter("id"));
                locataireclasse = dao.RechercheID(id);
                JsonElement jsonelemnt = gson.toJsonTree(locataireclasse);
                myObj.add("findlocataire", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("chargmnts")) {
                id = Integer.parseInt(request.getParameter("id"));
                chambreListe = dao.ListeDisop(id);
                JsonElement jsonelemnt = gson.toJsonTree(chambreListe);
                myObj.add("listeschambres", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            }
            request.getRequestDispatcher(forwds).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public Chambre ptfunction(Chambre chambre, int numchambre, String dispo) {
        chambre = dao.findChambre(numchambre);
        chambre.setIdchambre(chambre.getIdchambre());
        chambre.setSurfacechambre(chambre.getSurfacechambre());
        chambre.setQualitechambre(chambre.getQualitechambre());
        chambre.setLoyerchambre(chambre.getLoyerchambre());
        chambre.setDispochambre(dispo);
        return chambre;
    }
}
