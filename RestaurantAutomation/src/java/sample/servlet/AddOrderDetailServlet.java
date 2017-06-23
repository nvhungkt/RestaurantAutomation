/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tblmeal.TblMealDAO;
import sample.tblmeal.TblMealDTO;
import sample.tblorder.TblOrderDAO;
import sample.tblorder.TblOrderDTO;
import sample.tblorderdetail.TblOrderDetailDAO;
import sample.tblorderdetail.TblOrderDetailDTO;

/**
 *
 * @author Administrator
 */
public class AddOrderDetailServlet extends HttpServlet {

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
        
//        TblOrderDTO order = (TblOrderDTO) request.getAttribute("ORDER");
        int tableNumber = Integer.parseInt(request.getParameter("txtTableNumber"));
        
        String[] selectedIDs = request.getParameterValues("txtMealID");
        String[] quantitySelectedMeals = request.getParameterValues("txtQuantity");
        String url = "MiddleServlet?btAction=Take Order&txtTableNumber="
                + request.getParameter("txtTableNumber");
        
        try {
            TblOrderDTO order = new TblOrderDAO().getOrder(tableNumber);
            for (int i = 0; i < quantitySelectedMeals.length; i++) {
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(quantitySelectedMeals[i]);
                } catch (NumberFormatException e) {
                    log("AddOrderDetailServlet NumberFormatException" + e.getMessage());
                }
                if(quantity > 0) {
                    String orderID = order.getId();
                    int no;
                    if(order.getOrderDetails() == null) {
                        no = 1;
                    } else no = order.getOrderDetails().size() + 1;
                    String mealID = selectedIDs[i];
                    TblMealDAO dao = new TblMealDAO();
                    TblMealDTO meal = dao.loadMeal(mealID);
                    // already had quantity
                    Time takenTime = new Time(System.currentTimeMillis());
                    Time readyTime = null;
                    String cookID = null;
                    String status = "ordered";
                    
                    TblOrderDetailDTO oDetail = 
                            new TblOrderDetailDTO(no, meal, quantity, takenTime, readyTime, status, cookID);
                    // add to DB first
                    TblOrderDetailDAO oDDao = new TblOrderDetailDAO();
                    boolean result = oDDao.insertOrderDetail(oDetail, orderID);
                    if(result) {
                        // remove all list oD in order and reload from DB
                        order.setOrderDetails(null);
                        oDDao.loadOrderDetailByOrderID(orderID);
                        order.setOrderDetails(oDDao.getListOrderDetail());
                    }
                }
            }
        } catch (SQLException ex) {
            log("AddOrderDetailServlet SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddOrderDetailServlet NamingException: " + ex.getMessage());
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
