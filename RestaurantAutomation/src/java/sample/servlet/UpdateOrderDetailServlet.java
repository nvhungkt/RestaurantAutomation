/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tblorder.TblOrderDAO;
import sample.tblorder.TblOrderDTO;
import sample.tblorderdetail.TblOrderDetailDAO;

/**
 *
 * @author Administrator
 */
public class UpdateOrderDetailServlet extends HttpServlet {
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
        
        int tableNumber = Integer.parseInt(request.getParameter("txtTableNumber"));
        String[] quantityValues = request.getParameterValues("txtEditableQuantity");
        String[] orderDetailIDValues = request.getParameterValues("txtOrderDetailID");
        String urlRewriting = "MiddleServlet?"
                + "btAction=View+editable+order&"
                + "txtTableNumber=" + tableNumber;
        try {
            TblOrderDTO order = new TblOrderDAO().getOrder(tableNumber);
            int quantity = 0;
            TblOrderDetailDAO dao = new TblOrderDetailDAO();
            for (int i = 0; i < quantityValues.length; i++) {
                quantity = Integer.parseInt(quantityValues[i]);
                if(quantity > 0) {
                    dao.updateOrderDetailQuantity(order.getId(), orderDetailIDValues[i], quantity);
                } else if(quantity == 0) {
                    dao.deleteOrderDetail(order.getId(), orderDetailIDValues[i]);
                }
            }
        } catch (NamingException ex) {
            log("UpdateOrderDetailServlet NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateOrderDetailServlet SQLException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
