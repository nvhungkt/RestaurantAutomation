/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.timereport;

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

/**
 *
 * @author HT
 */
public class TimeReportServlet extends HttpServlet {

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
        
        String requestType = request.getParameter("reportType");
        
        TimeReportDAO dao = new TimeReportDAO();
        RequestDispatcher rd = request.getRequestDispatcher("timeReport.jsp");
        
        try {
            if (requestType != null) {
                int type = Integer.parseInt(requestType);
                if (year == null || year.equals("")) {
                    List<String> listYear = dao.getYearRange(type);
                    request.setAttribute("YEAR", listYear);
                } else if (month == null || month.equals("")) {
                    List<String> listYear = dao.getYearRange(type);
                    request.setAttribute("YEAR", listYear);
                    
                    List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year), type);
                    request.setAttribute("MONTH", listMonth);
                } else if (day == null || day.equals("")) {
                    List<String> listYear = dao.getYearRange(type);
                    request.setAttribute("YEAR", listYear);
                    
                    List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year), type);
                    request.setAttribute("MONTH", listMonth);
                    
                    List<String> listDay = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month), type);
                    request.setAttribute("DAY", listDay);
                    
                    int avgTimeInMonth = dao.getAvgTimeInMonth(Integer.parseInt(year), Integer.parseInt(month), type);
                    request.setAttribute("TIMEINMONTH", avgTimeInMonth);
                } else {
                    List<String> listYear = dao.getYearRange(type);
                    request.setAttribute("YEAR", listYear);
                    
                    List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year), type);
                    request.setAttribute("MONTH", listMonth);
                    
                    List<String> listDay = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month), type);
                    request.setAttribute("DAY", listDay);
                    
                    int avgTimeInDay = dao.getAvgTimeInDay(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), type);
                    request.setAttribute("TIMEINDAY", avgTimeInDay);
                    
                    if (type == 0) {
                        List<OrderDTO> list = dao.getTurnAroundTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                        request.setAttribute("ORDER", list);
                    } else if (type == 1) {
                        List<OrderDetailDTO> list = dao.getCookTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                        request.setAttribute("ORDERDETAIL", list);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log("TimeReportServlet:" + ex.getMessage());
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
