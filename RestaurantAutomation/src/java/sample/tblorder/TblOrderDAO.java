/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.naming.NamingException;
import sample.tblmeal.TblMealDTO;
import sample.tblorderdetail.TblOrderDetailDTO;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */
public class TblOrderDAO implements Serializable{
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    public TblOrderDTO getOrder(int tableNumber)
            throws NamingException, SQLException {
        
        TblOrderDTO order = null;
        ArrayList<TblOrderDetailDTO> list = null;
        
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT * FROM tblOrder WHERE "
                        + "tableNumber = ? AND date = ? AND leavingTime IS NULL");
                stm.setInt(1, tableNumber);
                
                java.util.Date date = new java.util.Date();
                Date sqlDate = new Date(date.getYear(), date.getMonth(), date.getDate());
                
                stm.setDate(2, sqlDate);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String orderID = rs.getString("id");
                    order = new TblOrderDTO(orderID);
                    stm = con.prepareStatement("SELECT tblOrderDetail.*, tblMeal.name AS mealName, "
                            + "unit, tblCategory.name AS cateName, price "
                            + "FROM tblOrderDetail, tblMeal, tblCategory, tblPrice "
                            + "WHERE orderID = ? AND tblOrderDetail.mealID = tblMeal.id AND "
                            + "tblMeal.id = tblPrice.mealID AND tblMeal.cateID = tblCategory.id "
                            + "AND toDate IS NULL");
                    stm.setString(1, orderID);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int no = rs.getInt("no");
                        String mealID = rs.getString("mealID");
                        int quantity = rs.getInt("quantity");
                        Time takenTime = rs.getTime("takenTime");
                        Time readyTime = rs.getTime("readyTime");
                        String status = rs.getString("status");
                        String cookID = rs.getString("cookID");
                        String mealName = rs.getString("mealName");
                        String unit = rs.getString("unit");
                        String cateName = rs.getString("cateName");
                        float price = rs.getFloat("price");
                        
                        if (list == null) list = new ArrayList();
                        list.add(new TblOrderDetailDTO(no, new TblMealDTO(mealID, mealName, unit, cateName, price),
                                quantity, takenTime, readyTime, status, cookID));
                    }
                    order.setOrderDetails(list);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return order;
    }
}
