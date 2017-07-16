/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.menureport;

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

/**
 *
 * @author HT
 */
public class MenuReportServlet extends HttpServlet {

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

        MenuReportDAO dao = new MenuReportDAO();
        RequestDispatcher rd = request.getRequestDispatcher("menuReport.jsp");

        try {
            if (year == null || year.equals("")) {
                List<String> listYear = dao.getYearRange();
                request.setAttribute("YEAR", listYear);
            } else if (month == null || month.equals("")) {
                List<String> listYear = dao.getYearRange();
                request.setAttribute("YEAR", listYear);

                List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", listMonth);
                
                Map<String, Integer> map = dao.getYearMenu(Integer.parseInt(year));
                request.setAttribute("YEARMEAL", map);
            } else if (day == null || day.equals("")) {
                List<String> listYear = dao.getYearRange();
                request.setAttribute("YEAR", listYear);

                List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", listMonth);

                List<String> listDay = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("DAY", listDay);

                Map<String, Integer> map = dao.getMonthMenu(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("MONTHMEAL", map);
                
                Map<Integer, MenuDTO> list = dao.getMonthMenuDetail(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("LIST", list);
            } else {
                List<String> listYear = dao.getYearRange();
                request.setAttribute("YEAR", listYear);

                List<String> listMonth = dao.getMonthsOf(Integer.parseInt(year));
                request.setAttribute("MONTH", listMonth);

                List<String> listDay = dao.getDatesOf(Integer.parseInt(year), Integer.parseInt(month));
                request.setAttribute("DAY", listDay);
                
                Map<String, Integer> map = dao.getDayMenu(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                request.setAttribute("DAYMEAL", map);
                
                Map<Integer, MenuDTO> list = dao.getDayMenuDetail(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                request.setAttribute("LIST", list);
            }
        } catch (SQLException | NamingException ex) {
            log("MenuReportServlet: " + ex.getMessage());
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
