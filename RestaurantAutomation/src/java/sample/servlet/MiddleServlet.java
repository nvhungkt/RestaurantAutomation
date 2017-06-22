/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tblstaff.TblStaffDTO;


/**
 *
 * @author Administrator
 */
public class MiddleServlet extends HttpServlet {
    private final String loginPage = "login.html";
    private final String loadOrderServlet = "LoadOrderServlet";
    private final String viewOrderListServlet = "ViewOrderListServlet";
    private final String changeMealStatusServlet = "ChangeMealStatusServlet";
    private final String loginServlet = "LoginServlet";
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
        HttpSession session = request.getSession();
        String button = request.getParameter("btAction"); 
        TblStaffDTO staff = (TblStaffDTO) session.getAttribute("STAFF");       
        String url = loginPage;        
        try {           
            if(staff != null) {
                switch(staff.getRole()) {
                    case "Waiter":
                        if(button==null) {
                            
                        }                        
                        else if(button.equals("Input table number")) {
                            url = loadOrderServlet;
                        }
                        break;
                    case "Kitchen":                        
                        if(button==null) {
                            url = viewOrderListServlet;
                        }
                        else if(button.equals("Finish") || button.equals("Cook")) {
                            url = changeMealStatusServlet;
                        }
                        else {
                            
                        }
                        break;
                        //defaul nay de test, nho xoa
                    default:
                        url = "LogoutServlet";
                }                                
            }
            else if(button != null) {
                url = loginServlet;
            }            
            
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
