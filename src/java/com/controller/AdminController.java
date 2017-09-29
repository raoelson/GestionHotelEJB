/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.LocataireDAOLocal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Djazz jah
 */
public class AdminController extends HttpServlet {

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
        String action = request.getParameter("actions");
        String forwds = "";
        String id = "";
        try {
            if (action.equalsIgnoreCase("wlcm")) {
                request.setAttribute("titles", "Backup");
                request.setAttribute("connecte", "Backup");
                forwds = "/sauve.jsp";
            } else if (action.equalsIgnoreCase("save")) {
                id = request.getParameter("id");
                JsonElement jsonelemnt = null;
                Calendar Cal = new GregorianCalendar();
                Date datees = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String now = sdf.format(datees);
                String dteheure = null;
                Date date = new Date();
                String heures = DateFormat.getTimeInstance().format(date);
                String  hours = heures.substring(0, 2);
                String  minutes = heures.substring(3, 5);
                String  seconds = heures.substring(6, 8);               
                dteheure  = now + "_" + hours + "-" + minutes + "-" + seconds;
                String user = "root";
                //C:\wamp\bin\mysql\mysql5.6.12\bin
                int processComplete;
                Process runtimeProcess = Runtime.getRuntime().exec("C:\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump -u " + user + " gestionhotel -r d:/backup/" + id + "_" + dteheure + ".sql");
                processComplete = runtimeProcess.waitFor();
                if (processComplete == 1) {//if values equal 1 process failed
                    request.setAttribute("sauve", "Backup Failed");
                    jsonelemnt = gson.toJsonTree("Backup Failed");
                } else if (processComplete == 0) {
                    //request.setAttribute("sauve", "Backup created Successfully..");
                    jsonelemnt = gson.toJsonTree("Backup created Successfully..");
                }
                myObj.add("message", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            } else if (action.equalsIgnoreCase("")) {
                request.setAttribute("titles", "Restore");
                request.setAttribute("connecte", "Restore");
                forwds = "/restaure.jsp";
            } else if (action.equalsIgnoreCase("stat")) {
                request.setAttribute("titles", "Stat");
                request.setAttribute("connecte", "Stat");
                forwds = "/Graph.jsp";
            } else if (action.equalsIgnoreCase("charts")) {
                JSONArray array = new JSONArray();
                JSONObject finalObject = new JSONObject();
                Map<String, Object> data = new HashMap<String, Object>();
                String mois[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                int i = 0;
                JSONObject j;
                String json = null;
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String cours = sdf.format(dt);                
                for (; i < mois.length; i++) {                     
                    data.put("value", dao.comptes(mois[i], cours));
                    j = new JSONObject(data);
                    array.put(j);
                    finalObject.put("student_data", array);
                }
                json = finalObject.toString();
                out.print(json);
                out.close();
            }else if(action.equalsIgnoreCase("restaure")){
                id = request.getParameter("id");
                String div = id.substring(12, 48);
                JsonElement jsonelemnt = null;
                int processComplete;
                String executeCmd = "C:/wamp/bin/mysql/mysql5.6.12/bin/mysql -u root gestionhotel < d:\\backup\\"+div;
                Process runtimeProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", executeCmd});
                processComplete = runtimeProcess.waitFor();
                if (processComplete == 1) {
                    jsonelemnt = gson.toJsonTree("Restore  Failed");
                } else if (processComplete == 0) {
                    jsonelemnt = gson.toJsonTree("Restore  created Successfully..");
                }
                myObj.add("message", jsonelemnt);
                out.println(myObj.toString());
                out.close();
            }
        } catch (Exception e) {
        }
        request.getRequestDispatcher(forwds).forward(request, response);
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
