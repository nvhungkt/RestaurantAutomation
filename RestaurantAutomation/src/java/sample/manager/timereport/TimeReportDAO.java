/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.timereport;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author HT
 */
public class TimeReportDAO implements Serializable {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    //reportType: 0-turnaround; 1-preparation
    
    public List<String> getYearRange(int reportType) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[3];
                sql[0] = "SELECT DISTINCT DATEPART(YEAR, date) FROM tblOrder";
                sql[1] = "SELECT DISTINCT DATEPART(YEAR, date) "
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail)";

                stm = con.prepareStatement(sql[reportType]);

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

    public List<String> getMonthsOf(int year, int reportType) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[2];
                sql[0] = "SELECT DISTINCT DATEPART(MONTH, date) FROM tblOrder WHERE DATEPART(YEAR, date) = ?";
                sql[1] = "SELECT DISTINCT DATEPART(MONTH, date) "
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ?";
                
                stm = con.prepareStatement(sql[reportType]);
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
    
    public List<String> getDatesOf(int year, int month, int reportType) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[2];
                sql[0] = "SELECT DISTINCT DATEPART(DAY, date) FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?";
                sql[1] = "SELECT DISTINCT DATEPART(DAY, date) FROM tblOrder WHERE id IN (SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?";
                
                stm = con.prepareStatement(sql[reportType]);
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
    
    public int getAvgTimeInMonth(int year, int month, int reportType) throws NamingException, SQLException {
        int avgTime = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[2];
                sql[0] = "SELECT AVG(r.avgTime)FROM(SELECT DATEDIFF(MINUTE, arrivingTime, leavingTime) as avgTime FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?) r";
                sql[1] = "SELECT AVG(DATEDIFF(MINUTE, takenTime, readyTime)) FROM tblOrderDetail WHERE orderID IN (SELECT id FROM tblOrder WHERE id IN (SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?)";
                
                stm = con.prepareStatement(sql[reportType]);
                stm.setInt(1, year);
                stm.setInt(2, month);

                rs = stm.executeQuery();
                while (rs.next()) {
                    avgTime = rs.getInt(1);
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
        return avgTime;
    }
    
    public int getAvgTimeInDay(int year, int month, int day, int reportType) throws NamingException, SQLException {
        int avgTime = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[2];
                sql[0] = "SELECT AVG(DATEDIFF(MINUTE, arrivingTime, leavingTime)) FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ? AND DATEPART(DAY, date) = ?";
                sql[1] = "SELECT AVG(DATEDIFF(MINUTE, takenTime, readyTime)) FROM tblOrderDetail WHERE orderID IN (SELECT id FROM tblOrder WHERE id IN (SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ? AND DATEPART(DAY, date) = ?)";
                
                stm = con.prepareStatement(sql[reportType]);
                stm.setInt(1, year);
                stm.setInt(2, month);
                stm.setInt(3, day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    avgTime = rs.getInt(1);
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
        return avgTime;
    }
    
    public List<OrderDTO> getTurnAroundTime(int year, int month, int day) throws NamingException, SQLException {
        List<OrderDTO> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT *, DATEDIFF(MINUTE, arrivingTime, leavingTime) as avgTime FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ? AND DATEPART(DAY, date) = ?";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);
                stm.setInt(3, day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    int tableNo = rs.getInt(2);
                    Time arrivingTime = rs.getTime("arrivingTime");
                    Time leavingTime = rs.getTime("leavingTime");
                    int avgTime = rs.getInt("avgTime");
                    
                    list.add(new OrderDTO(id, tableNo, arrivingTime, leavingTime, avgTime));
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
    
    public List<OrderDetailDTO> getCookTime(int year, int month, int day) throws NamingException, SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT orderID, m.name, o.quantity, s.name, DATEDIFF(MINUTE, takenTime, readyTime) as avgTime FROM tblOrderDetail o, tblMeal m, tblStaff s WHERE mealID = m.id AND o.cookID = s.id AND orderID IN (SELECT id FROM tblOrder WHERE id IN (SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ? AND DATEPART(DAY, date) = ?)";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);
                stm.setInt(3, day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString(1);
                    String mealName = rs.getString(2);
                    int quantity = new Float(rs.getFloat(3)).intValue();
                    String cookName = rs.getString(4);
                    int avgTime = rs.getInt(5);
                    
                    list.add(new OrderDetailDTO(orderID, mealName, quantity, cookName, avgTime));
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
}
