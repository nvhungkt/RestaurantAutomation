/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tblorderdetail.TblOrderDetailDAO;
import sample.tblstaff.TblStaffDTO;

/**
 *
 * @author Turtle
 */
public class ChangeMealStatusServlet extends HttpServlet {
    private final String middleServlet = "MiddleServlet";
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
        String button = request.getParameter("btAction");
        String orderId = request.getParameter("txtOrderId");
        int orderNo = Integer.parseInt(request.getParameter("txtOrderNo"));
        String url = middleServlet;
        try {
            TblOrderDetailDAO dao = new TblOrderDetailDAO();
            String status;
            if(button.equals("Finish")) status = "ready";
            else status = "cooking";
            HttpSession session = request.getSession();
            TblStaffDTO staff = (TblStaffDTO) session.getAttribute("STAFF");
            int result = dao.changeStatus(orderId, orderNo, status, staff.getId());         
        }
        catch(SQLException ex) {
            log("ChangeMealStatusServlet_ SQL error " + ex.getMessage());
        }
        catch(NamingException ex) {
            log("ChangeMealStatusServlet_ Datasource error " + ex.getMessage());
        }
        finally {
            response.sendRedirect(url);
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
