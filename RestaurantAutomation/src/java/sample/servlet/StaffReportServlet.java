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
import sample.manager.StaffDAO;
import sample.tblstaff.TblStaffDTO;

/**
 *
 * @author HT
 */
public class StaffReportServlet extends HttpServlet {

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
        
        String staffRoleStr = request.getParameter("staffRole");
        int staffRole = 0;
        if (staffRoleStr != null)
            staffRole = Integer.parseInt(staffRoleStr);
        
        StaffDAO dao = new StaffDAO();
        RequestDispatcher rd = request.getRequestDispatcher("busboyReport.jsp");
        
        try  {
            
            if (staffRoleStr != null) {
                List<TblStaffDTO> staffList = dao.getAllStaffOf(staffRole);
                request.setAttribute("STAFFLIST", staffList);
                
                if (year == null) {
                    List<String> list = dao.getYearRange(staffRole);
                    request.setAttribute("YEAR", list);
                } else if (month == null || month.equals("")) {
                    List<String> list1 = dao.getYearRange(staffRole);
                    request.setAttribute("YEAR", list1);
                    
                    List<String> list2 = dao.getMonthsOf(Integer.parseInt(year), staffRole);
                    request.setAttribute("MONTH", list2);
                } else {
                    List<String> list1 = dao.getYearRange(staffRole);
                    request.setAttribute("YEAR", list1);
                    
                    List<String> list2 = dao.getMonthsOf(Integer.parseInt(year), staffRole);
                    request.setAttribute("MONTH", list2);
                    
                    Map<Integer, TblStaffDTO> map = dao.getMonthlyStaffEfficiency(Integer.parseInt(year), Integer.parseInt(month), staffRole);
                    request.setAttribute("RESULT", map);
                }
            }
        } catch (NamingException | SQLException ex) {
            log("StaffReportServlet: " + ex.getMessage());
        } finally {
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
