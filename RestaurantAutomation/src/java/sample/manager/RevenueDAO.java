/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author HT
 */
public class RevenueDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<String> getYearRange() throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT DATEPART(YEAR, date) "
                        + "FROM tblOrder";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt(1)));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<String> getMonthsOf(int year) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT DATEPART(MONTH, date) "
                        + "FROM tblOrder "
                        + "WHERE DATEPART(YEAR, date) = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, year);

                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt(1)));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public List<String> getDatesOf(int year, int month) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT DATEPART(DAY, date) "
                        + "FROM tblOrder "
                        + "WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);

                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt(1)));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public double getYeatRevenue(int year) throws NamingException, SQLException {
        double revenue = 0;
        ResultSet rs2;
        CallableStatement stm2;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT id FROM tblOrder WHERE DATEPART(YEAR, date) = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, year);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString(1);
                    
                    stm2 = con.prepareCall("{call getBillWithOrderID(?)}");
                    stm2.setString(1, orderID);
                    
                    rs2 = stm2.executeQuery();
                    if (rs2.next())
                        revenue += rs2.getFloat(1);
                    rs2.close();
                    stm2.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return revenue;
    }
    
    public double getMonthRevenue(int year, int month) throws NamingException, SQLException {
        double revenue = 0;
        ResultSet rs2;
        CallableStatement stm2;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT id FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString(1);
                    
                    stm2 = con.prepareCall("{call getBillWithOrderID(?)}");
                    stm2.setString(1, orderID);
                    
                    rs2 = stm2.executeQuery();
                    if (rs2.next())
                        revenue += rs2.getFloat(1);
                    rs2.close();
                    stm2.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return revenue;
    }
    
    public double getDateRevenue(int year, int month, int day) throws NamingException, SQLException {
        double revenue = 0;
        ResultSet rs2;
        CallableStatement stm2;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT id FROM tblOrder WHERE date = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, year + "-" + month + "-" + day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString(1);
                    
                    stm2 = con.prepareCall("{call getBillWithOrderID(?)}");
                    stm2.setString(1, orderID);
                    
                    rs2 = stm2.executeQuery();
                    if (rs2.next())
                        revenue += rs2.getFloat(1);
                    rs2.close();
                    stm2.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return revenue;
    }
    
    public Map<String, Float> getOrdersInDate(int year, int month, int day) throws NamingException, SQLException {
        Map<String, Float> map = new HashMap<>();
        ResultSet rs2;
        CallableStatement stm2;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT id FROM tblOrder WHERE date = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, year + "-" + month + "-" + day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString(1);
                    
                    stm2 = con.prepareCall("{call getBillWithOrderID(?)}");
                    stm2.setString(1, orderID);
                    
                    rs2 = stm2.executeQuery();
                    if (rs2.next())
                        map.put(orderID, rs2.getFloat(1));
                    
                    rs2.close();
                    stm2.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return map;
    }
    
    public List<OrderDetailReportDTO> getDetailsOf(String orderID, String date) throws NamingException, SQLException {
        List<OrderDetailReportDTO> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT orderID, name, quantity, price "
                        + "FROM tblOrderDetail, tblMeal, tblPrice "
                        + "WHERE orderID = ? AND tblOrderDetail.mealID = id AND tblPrice.mealID = id AND fromDate <= ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, date);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mealName = rs.getString(2);
                    int quantity = (int)rs.getFloat(3);
                    float price = rs.getFloat(4);
                    
                    OrderDetailReportDTO dto = new OrderDetailReportDTO(orderID, mealName, quantity, price);
                    list.add(dto);
                }
            }//end if con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
