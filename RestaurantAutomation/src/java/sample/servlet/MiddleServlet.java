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
    private final String updateOrderDetailServlet = "UpdateOrderDetailServlet";
    private final String removeOrderDetailServlet = "RemoveOrderDetailServlet";
    private final String addOrderDetailServlet = "AddOrderDetailServlet";
    private final String takeOrderServlet = "TakeOrderServlet";
    private final String checkOutServlet = "CheckOutServlet";
    private final String useTableServlet = "UseTableServlet";
    private final String viewOrderListServlet = "ViewOrderListServlet";
    private final String changeMealStatusServlet = "ChangeMealStatusServlet";
    private final String loginServlet = "LoginServlet";
    private final String viewTableServlet = "ViewTableServlet";
    private final String viewEditableOrderDetailsPage = "viewEditableOrderDetails.jsp";
    private final String serveFoodServlet = "ServeFoodServlet";
    private final String finishCheckOutServlet = "FinishCheckOutServlet";
    private final String cleanTableServlet = "CleanTableServlet";
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
                if(button == null) {
                    if (staff.getRole().equals("manager"))
                        url = "manager.jsp";
                    else if (staff.getRole().equals("cook"))
                        url = viewOrderListServlet;
                    else url = viewTableServlet;
                } else if(button.equals("Update order") && staff.getRole().equals("waiter")) {
                    url = updateOrderDetailServlet;
                } else if(button.equals("Remove order") && staff.getRole().equals("waiter")) {
                    url = removeOrderDetailServlet;
                } else if(button.equals("View editable order") && staff.getRole().equals("waiter")) {
                    url = loadOrderServlet;
                } else if(button.equals("Add order") && staff.getRole().equals("waiter")) {
                    url = addOrderDetailServlet;
                } else if(button.equals("Input table number") && staff.getRole().equals("waiter")) {
                    url = loadOrderServlet;
                } else if (button.equals("Take Order")) {
                    url = takeOrderServlet;
                } else if (button.equals("Check Out")) {
                    url = checkOutServlet;
                } else if (button.equals("Finish check out")) {
                    url = finishCheckOutServlet;
                } else if (button.equals("Use Table")) {
                    url = useTableServlet;
                } else if (button.equals("Clean Table")) {
                    url = cleanTableServlet;
                } else if(button.equals("Finish") || button.equals("Cook")) {
                    url = changeMealStatusServlet;
                } else if(button.equals("Serve")) {
                    url = serveFoodServlet;
                }
                
                if (staff.getRole().equals("manager")) {
                    if (button == null) {
                        
                    } else if (button.equals("ManageTable")) {
                        url = "ManageTableServlet";
                    } else if (button.equals("ManageMeal")) {
                        url = "ManageMealServlet";
                    } else if (button.equals("report")) {
                        url = "report.jsp";
                    }
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
