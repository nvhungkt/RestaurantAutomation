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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.manager.OrderDetailReportDTO;
import sample.manager.RevenueDAO;

/**
 *
 * @author HT
 */
public class RevenueServlet extends HttpServlet {

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
        
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        
        String orderID = request.getParameter("orderID");
        
        RevenueDAO dao = new RevenueDAO();
        RequestDispatcher rd = request.getRequestDispatcher("revenueReport.jsp");
        
        try {
            if (year == null) {
                List<String> list = dao.getYearRange();
                request.setAttribute("YEAR", list);
                rd.forward(request, response);
            } else if (month == null || month.equals("")) {
                List<String> list1 = dao.getYearRange();
                request.setAttribute("YEAR", list1);
                
                List<String> list2 = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", list2);
                
                double yearRevenue = dao.getYearRevenue(Integer.parseInt(year));
                request.setAttribute("YEARREVENUE", yearRevenue);
                rd.forward(request, response);
            } else if (day == null || day.equals("")) {
                List<String> list1 = dao.getYearRange();
                request.setAttribute("YEAR", list1);
                
                List<String> list2 = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", list2);
                
                List<String> list = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("DAY", list);
                
                double monthRevenue = dao.getMonthRevenue(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("MONTHREVENUE", monthRevenue);
                rd.forward(request, response);
            } else {
                List<String> list1 = dao.getYearRange();
                request.setAttribute("YEAR", list1);
                
                List<String> list2 = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", list2);
                
                List<String> list3 = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("DAY", list3);
                
                double dayRevenue = dao.getDateRevenue(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                request.setAttribute("DAYREVENUE", dayRevenue);
                
                Map<String, Float> map = dao.getOrdersInDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                request.setAttribute("ORDERLIST", map);
                
                if (orderID != null) {
                    List<OrderDetailReportDTO> list = dao.getDetailsOf(orderID, year + "-" + month + "-" + day);
                    request.setAttribute("ORDERDETAIL", list);
                }
                rd.forward(request, response);
            }
        } catch (NamingException | SQLException ex) {
            log("RevenueServlet: " + ex.getMessage());
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
