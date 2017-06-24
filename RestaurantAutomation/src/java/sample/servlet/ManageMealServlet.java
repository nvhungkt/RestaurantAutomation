/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tblcategory.TblCategoryDAO;
import sample.tblcategory.TblCategoryDTO;
import sample.tblmeal.TblMealDAO2;
import sample.tblmeal.TblMealDTO2;

/**
 *
 * @author VT
 */
public class ManageMealServlet extends HttpServlet {

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
        
        String url = "MiddleServlet?btAction=ManageMeal";
        
        String button = request.getParameter("mealAction");
        String mealID = request.getParameter("mealID");
        String mealName = request.getParameter("mealName");
        String unit = request.getParameter("unit");
        String price = request.getParameter("price");
        String cateID = request.getParameter("category");
        
        try {
            TblMealDAO2 mealDao = new TblMealDAO2();
            TblCategoryDAO cateDao = new TblCategoryDAO();
            
            if (button == null) {
                mealDao.loadAllMeals();
                List<TblMealDTO2> meal = mealDao.getList();
                request.setAttribute("MEAL", meal);
                
                cateDao.loadCategory();
                List<TblCategoryDTO> category = cateDao.getList();
                request.setAttribute("CATEGORY", category);
                
                RequestDispatcher rd = request.getRequestDispatcher("mealManagement.jsp");
                rd.forward(request, response);
            } else {
                if (price != null && !price.matches("\\d+([.]\\d*)?")) {
                    request.setAttribute("ERROR", "Price must be a float!! <br/>");
                    
                    cateDao.loadCategory();
                    List<TblCategoryDTO> category = cateDao.getList();
                    request.setAttribute("CATEGORY", category);
                    
                    RequestDispatcher rd = request.getRequestDispatcher("mealManagement.jsp");
                    rd.forward(request, response);
                } else if (button.equals("Add")) {
                    mealDao.addMeal(mealName, unit, BigDecimal.valueOf(Double.parseDouble(price)), cateID);
                    
                    response.sendRedirect(url);
                } else if (button.equals("Delete")) {
                    mealDao.removeMeal(mealID);
                    
                    response.sendRedirect(url);
                } else if (button.equals("Update")) {
                    mealDao.updateMeal(mealID, mealName, unit, BigDecimal.valueOf(Double.parseDouble(price)), cateID);
                    
                    response.sendRedirect(url);
                }
            }
        } catch (NamingException ex) {
            log("ManageMealServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ManageMealServlet _ SQL: " + ex.getMessage());
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
