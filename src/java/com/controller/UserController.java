/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.UtilisateurDAOLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Djazz jah
 */
public class UserController extends HttpServlet {

    @EJB
    private UtilisateurDAOLocal dao;

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
        List<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
        Utilisateur classes = new Utilisateur();
        HttpSession session = request.getSession();
        try {
            if (action.equals("authentification")) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String shapassword = SHAConverter(password);
                utilisateur = dao.verif(login, shapassword);   
                for(Object ut : utilisateur){
                     session.setAttribute("session", ut);                   
                }                 
                JsonElement jsonelement = gson.toJsonTree(utilisateur);
                myObj.add("verify", jsonelement);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("wlcm")) {
                request.setAttribute("titles", "Admin");
                request.setAttribute("connecte", "Admin");
                forwds = "/User.jsp";
            } else if (action.equalsIgnoreCase("listesusers")) {
                utilisateur = dao.ListeUser();
                JsonElement jsonelemnt = gson.toJsonTree(utilisateur);
                myObj.add("listesusers", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("delete")) {
                id = Integer.parseInt(request.getParameter("id"));
                dao.SuppressionUser(id);
                JsonElement jsonelemnt = gson.toJsonTree(id);
                myObj.add("id", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("recherches")) {
                id = Integer.parseInt(request.getParameter("id"));
                classes = dao.RechercherId(id);
                JsonElement jsonelemnt = gson.toJsonTree(classes);
                myObj.add("findusers", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("create")) {
                id = Integer.parseInt(request.getParameter("id"));
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String shapassword = SHAConverter(password);
                String type = request.getParameter("type");

                classes.setLogin(login);
                classes.setPassword(shapassword);
                classes.setType(type);
                if (id != 0) {
                    classes.setId(id);
                    dao.ModificationUser(classes);
                } else {
                    dao.addUser(classes);
                }
                JsonElement jsonelement = gson.toJsonTree(classes);
                if (classes == null) {
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

    public String SHAConverter(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, "SHAConverter.getAsObject:" + unsupportedEncodingException);
            return "";
        }
    }
}
