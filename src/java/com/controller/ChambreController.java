/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.ChambreDAOLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.Chambre;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
public class ChambreController extends HttpServlet {

    @EJB
    private ChambreDAOLocal dao;

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
        List<Chambre> chambre = new ArrayList<Chambre>();
        try {
            String forwds = "";
            String action = request.getParameter("actions");
            String id = "";
            int ids;
            if (action.equalsIgnoreCase("wlcm")) {
                request.setAttribute("titles", "Chambre");
                request.setAttribute("connecte", "Chambre");
                forwds = "/Chambrejson.jsp";
            } else if (action.equalsIgnoreCase("listeschambre")) {
                chambre = dao.getAllChambre();
                JsonElement jsonelemnt = gson.toJsonTree(chambre);
                myObj.add("listeschambres", jsonelemnt);
                out.println(myObj.toString());                
                out.close();
            } else if (action.equalsIgnoreCase("delete")) {
                id = request.getParameter("id");
                ids = id.equals("") ? 0 : Integer.parseInt(id);
                dao.deleteChambre(ids);
                JsonElement jsonelemnt = gson.toJsonTree(ids);
                myObj.add("id", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("recherches")) {
                id = request.getParameter("id");
                ids = id.equals("") ? 0 : Integer.parseInt(id);
                Chambre ch = dao.RechercheID(ids);
                JsonElement jsonelemnt = gson.toJsonTree(ch);
                myObj.add("findchambre", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("create")) {
                id = request.getParameter("id");
                ids = id.equals("") ? 0 : Integer.parseInt(id);
                String surface = request.getParameter("surface");
                String qualite = request.getParameter("qualite");
                String dispo = request.getParameter("occupation");
                String loyer = request.getParameter("loyer");
                double loyrs = Double.parseDouble(loyer);
                BigDecimal loyers = BigDecimal.valueOf(loyrs);
                Chambre ch = new Chambre();
                ch.setSurfacechambre(surface);
                ch.setQualitechambre(qualite);
                ch.setLoyerchambre(loyers);
                ch.setDispochambre(dispo);
                if (id.equalsIgnoreCase("")) {
                    dao.addChambre(ch);
                } else {
                    ch.setIdchambre(ids);
                    dao.editChambre(ch);
                }
                JsonElement jsonelement = gson.toJsonTree(ch);
                if (ch == null) {
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
}
