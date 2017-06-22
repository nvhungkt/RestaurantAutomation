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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tblorder.TblOrderDAO;
import sample.tblorder.InputTableNumberError;
import sample.tblorder.TblOrderDTO;

/**
 *
 * @author Administrator
 */
public class LoadOrderServlet extends HttpServlet {
    private final String inputTableNumberPage = "inputTableNumber.jsp";
    private final String getMenuServlet = "GetMenuServlet";
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
        String tableNum = request.getParameter("txtInputTableNum");
        String task = request.getParameter("txtTask");
        String url = inputTableNumberPage;
        InputTableNumberError errors = new InputTableNumberError();
        boolean errorFound = false;
        
        try {
            
            if(tableNum.trim().length() == 0) {
                errors.setTableNumberFormatError("Table number not allow empty !!!");
                errorFound = true;
            } else {
                int id = 0;
                try {
                    id = Integer.parseInt(tableNum);
                    if(id <= 0) {
                        errors.setTableNumberFormatError("Table number must larger than 0 !!!");
                        errorFound = true;
                    }
                } catch (NumberFormatException e) {
                    errors.setTableNumberFormatError("Table number must be integer !!!");
                    errorFound = true;
                    log("LoadOrderServlet NumberFormatException: " + e.getMessage());
                }
                if(errorFound) {
                    session.setAttribute("INPUTTABLENUMBERERROR", errors);
                } else {
                    TblOrderDAO dao = new TblOrderDAO();
                    boolean result = dao.loadOrderByTableNubmer(id);
                    if(result) {
                        List<TblOrderDTO> listOrder = dao.getListOrder();
                        session.setAttribute("ORDER", listOrder.get(0));
                        if(task.equals("insert order detail")) {
                            url = getMenuServlet;
                        }
                    } else {
                        errors.setNotFoundError("This table not have order !!!");
                        session.setAttribute("INPUTTABLENUMBERERROR", errors);
                    }
                }
            }
            
        } catch (NamingException ex) {
            log("LoadOrderServlet NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoadOrderServlet SQLException: " + ex.getMessage());
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
