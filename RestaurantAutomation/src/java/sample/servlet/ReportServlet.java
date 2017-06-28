/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.manager.ManagerDAO;
import sample.manager.RevenuePerDateObj;

/**
 *
 * @author VT
 */
public class ReportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String button = request.getParameter("reportAction");
        
        String url = "report.jsp";
        
        try {
            ManagerDAO dao = new ManagerDAO();
            
            if (button == null) {

            } else if (button.equals("Revenue")) {
                request.setAttribute("REVENUEFLAG", "REVENUEFLAG");
                
                List<RevenuePerDateObj> list = dao.loadRevenuePerDate();
                request.setAttribute("LIST", list);
                
                double total = dao.getTotalRevenue();
                request.setAttribute("TOTAL", total);
            } else if (button.equals("Efficiency")) {
                request.setAttribute("EFFICIENCYFLAG", "EFFICIENCYFLAG");
            } else if (button.equals("Time")) {
                request.setAttribute("TIMEFLAG", "TIMEFLAG");
                
                int turnAround = dao.getAvgTurnAroundTime();
                request.setAttribute("TURNAROUND", turnAround);
                
                int prepareTime = dao.getAvgPreparationTime();
                request.setAttribute("PREPARATION", prepareTime);
            } else if (button.equals("Menu")) {
                request.setAttribute("MENUFLAG", "MENUFLAG");
                
                List<String> list = dao.getMostPopularItem();
                request.setAttribute("MOSTPOPMEAL", list);
            } else if (button.equals("Busboy")) {
                
                request.setAttribute("EFFICIENCYFLAG", "EFFICIENCYFLAG");
                
                List<String> list = dao.getBusboyEfficiency();
                request.setAttribute("BUSBOY", list);
            } else if (button.equals("Waiter")) {
                
                request.setAttribute("EFFICIENCYFLAG", "EFFICIENCYFLAG");
                
                List<String> list = dao.getWaiterEfficiency();
                request.setAttribute("WAITER", list);
            } else if (button.equals("Cook")) {
                
                request.setAttribute("EFFICIENCYFLAG", "EFFICIENCYFLAG");
                
                List<String> list = dao.getKitchenEfficiency();
                request.setAttribute("COOK", list);
            }
        } catch (NamingException ex) {
            log("ReportServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ReportServlet _ SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
