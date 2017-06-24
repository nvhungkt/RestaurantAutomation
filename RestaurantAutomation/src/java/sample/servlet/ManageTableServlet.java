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
import sample.tbltable.TblTableDAO;
import sample.tbltable.TblTableDTO;

/**
 *
 * @author VT
 */
public class ManageTableServlet extends HttpServlet {

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
        String button = request.getParameter("tableAction");
        String number = request.getParameter("tableNumber");
        String capacity = request.getParameter("capacity");
                
        String url = "MiddleServlet?btAction=ManageTable";
        
        try {
            TblTableDAO dao = new TblTableDAO();
            
            if (button == null) {
                List<TblTableDTO> list = dao.getTables();
                
                request.setAttribute("TABLE", list);
                
                RequestDispatcher rd = request.getRequestDispatcher("tableManagement.jsp");
                rd.forward(request, response);
            } else {
                if (!number.matches("\\d+")) {
                    response.sendRedirect(url);
                } else if (button.equals("delete")) {
                    //dao.removeTable(Integer.parseInt(number));

                    response.sendRedirect(url);
                } else if (button.equals("Update")) {
                    //dao.updateTable(Integer.parseInt(number), Integer.parseInt(capacity));

                    response.sendRedirect(url);
                } else if (button.equals("Add")) {
                    /*boolean exist = dao.checkTableExist(Integer.parseInt(number));

                    if (!exist) {
                        dao.updateTable(Integer.parseInt(number), Integer.parseInt(capacity));
                        response.sendRedirect(url);
                    } else {
                        request.setAttribute("ERROR", "This table number is already exist <br/>");
                        RequestDispatcher rd = request.getRequestDispatcher("tableManagement.jsp");
                        rd.forward(request, response);
                    }*/
                }
            }
        } catch (NamingException ex) {
            log("ManageTableServlet Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ManageTableServlet SQL: " + ex.getMessage());
        } finally {
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
