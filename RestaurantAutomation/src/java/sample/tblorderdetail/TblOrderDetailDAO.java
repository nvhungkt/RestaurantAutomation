/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorderdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */
public class TblOrderDetailDAO implements Serializable{
    List<OrderDetail> orderList;

    public List<OrderDetail> getOrderList() {
        return orderList;
    }
    public int changeStatus(String orderId, String no, String newStatus, String cookId) throws SQLException, NamingException {
        Connection con =null;
        PreparedStatement stm = null;        
        try {            
            con = DBUtilities.makeConnection();    
            String sql;
            Time time =null;
            if(newStatus.equals("COOKED")) {
                time = new Time(System.currentTimeMillis());                
            }
            sql = "UPDATE tblOrderDetail SET status = ?, cookID = ?, readyTime = ? WHERE orderID = ? AND no = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, newStatus);
            stm.setString(2, cookId);
            stm.setTime(3, time);
            stm.setString(4, orderId);
            stm.setString(5, no);
            return stm.executeUpdate();
            
        }
        finally {           
            if(stm!=null) {
                stm.close();
            }
            if(con!=null) {
                con.close();
            }
        }
    }
    public void getList() throws NamingException, SQLException {
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "SELECT o.orderID AS OrderID, o.no, m.name AS MealName, "
                    + "m.unit AS MealUnit,  o.quantity, c.name AS Category, o.takenTime, o.status " +
                        "FROM tblOrderDetail o, " +
                        "(SELECT * " +
                        "FROM tblMeal) m, " +
                        "(SELECT * " +
                        "FROM tblCategory) c " +
                        "WHERE o.cookID = m.id AND c.id = m.cateID AND o.status NOT LIKE 'COOKED'";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if(orderList==null) {
                orderList = new ArrayList<OrderDetail>();
            }
           while(rs.next()) {
               String orderId = rs.getString("OrderID");
               String no = rs.getString("no");
               String mealName = rs.getString("MealName");
               String mealUnit = rs.getString("MealUnit");
               BigDecimal quantity = rs.getBigDecimal("quantity");
               String cate = rs.getString("Category");
               Time takenTime = rs.getTime("takenTime");
               String status = rs.getString("status");
               OrderDetail order = new OrderDetail(orderId, no, mealName, mealUnit, quantity, cate, takenTime, status);
               orderList.add(order);
           }
        }
        finally {
            if(rs!=null) {
                rs.close();
            }
            if(stm!=null) {
                stm.close();
            }
            if(con!=null) {
                con.close();
            }
        }
        
    }
}
