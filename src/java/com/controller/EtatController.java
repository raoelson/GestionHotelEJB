/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.LocataireDAOLocal;
import com.DAO.PdfDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.Locataire;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class EtatController extends HttpServlet {

    @EJB
    private LocataireDAOLocal dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String forwds = "";
        int id;
        String action = request.getParameter("actions");
        List<Locataire> locataire = new ArrayList<Locataire>();
        Locataire lc = new Locataire();
        try {
            if (action.equalsIgnoreCase("wlcm")) {
                request.setAttribute("titles", "Etat de Paie");
                request.setAttribute("connecte", "Etat de Paie");
                forwds = "/etatpaie.jsp";
            } else if (action.equalsIgnoreCase("etats")) {

                id = Integer.parseInt(request.getParameter("id"));
                locataire = dao.EtatPaie(id);
                JsonElement jsonelemnt = gson.toJsonTree(locataire);
                myObj.add("etatpaie", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("csv")) {
                Calendar Cal = new GregorianCalendar();
                Date datees = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String now = sdf.format(datees);
                int second = Cal.get(Calendar.SECOND);
                int minute = Cal.get(Calendar.MINUTE);
                int hour = Cal.get(Calendar.HOUR);
                String dteheure = now + "_" + hour + "-" + minute + "-" + second;
                id = Integer.parseInt(request.getParameter("id"));
                String file = "D:\\ReportingEtatPaie" + dteheure;
                FileWriter fw = new FileWriter(file + ".csv");
                fw.append("Numero Locataire");
                fw.append(";");
                fw.append("Nom Locataire");
                fw.append(";");
                fw.append("Prenom Locataire");
                fw.append(";");
                fw.append("Numero Chambre");
                fw.append(";");
                fw.append("Date Arrivee");
                fw.append(";");
                fw.append("Montant ");
                fw.append("\n");
                lc = dao.RechercheID(id);
                String idl = null;
                idl = idl.valueOf(lc.getIdlocataire());
                String idc = null;
                idc = idc.valueOf(lc.getIdchambre().getIdchambre());
                String loyer = null;
                loyer = loyer.valueOf(lc.getIdchambre().getLoyerchambre());
                fw.append(idl);
                fw.append(";");
                fw.append(lc.getNomlocataire());
                fw.append(";");
                fw.append(lc.getPrenomlocataire());
                fw.append(";");
                fw.append(idc);
                fw.append(";");
                fw.append(lc.getDatearrivee());
                fw.append(";");
                fw.append(loyer);
                fw.append("\n");
                fw.flush();
                fw.close();
                JsonElement jsonelement = gson.toJsonTree(lc);
                if (lc == null) {
                    myObj.addProperty("success", false);
                } else {
                    myObj.addProperty("success", true);
                }
                myObj.add("donnees", jsonelement);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("pdf")) {
                id = Integer.parseInt(request.getParameter("id"));
                PdfDAO pdfs = new PdfDAO();
                lc = dao.RechercheID(id);
                String idl = null;
                idl = idl.valueOf(lc.getIdlocataire());
                String idc = null;
                idc = idc.valueOf(lc.getIdchambre().getIdchambre());
                String loyer = null;
                loyer = loyer.valueOf(lc.getIdchambre().getLoyerchambre());
                pdfs.pdf(idl, lc.getNomlocataire(), lc.getPrenomlocataire(), idc, lc.getDatearrivee(), loyer);
                JsonElement jsonelement = gson.toJsonTree(lc);
                if (lc == null) {
                    myObj.addProperty("success", false);
                } else {
                    myObj.addProperty("success", true);
                }
                myObj.add("donnees", jsonelement);
                out.println(myObj.toString());
                out.close();
            }
            request.getRequestDispatcher(forwds).forward(request, response);
        } catch (Exception e) {
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
}
