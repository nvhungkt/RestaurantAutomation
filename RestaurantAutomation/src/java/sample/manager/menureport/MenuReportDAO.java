/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.menureport;

import java.io.Serializable;
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
public class MenuReportDAO implements Serializable {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    public List<String> getYearRange() throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT DATEPART(YEAR, date) "
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail)";

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
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ?";
                
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
                String sql = "SELECT DISTINCT DATEPART(DAY, date) FROM tblOrder WHERE id IN (SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?";
                
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
    
    public Map<String, Integer> getYearMenu(int year) throws NamingException, SQLException {
        Map<String, Integer> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT m.name, COUNT(*) as times \n"
                        + "FROM tblOrderDetail o, tblMeal m \n"
                        + "WHERE o.mealID = m.id AND orderID IN \n"
                        + "	(SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ?) \n"
                        + "GROUP BY m.name\n"
                        + "HAVING COUNT(*) >=\n"
                        + "	(SELECT MAX(r.times)\n"
                        + "	FROM \n"
                        + "	(SELECT m.name, COUNT(*) as times \n"
                        + "	FROM tblOrderDetail o, tblMeal m \n"
                        + "	WHERE o.mealID = m.id AND orderID IN \n"
                        + "		(SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ?) \n"
                        + "	GROUP BY m.name) r)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, year);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String mealName = rs.getString(1);
                    int times = rs.getInt(2);
                    
                    map.put(mealName, times);
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
    public Map<String, Integer> getMonthMenu(int year, int month) throws NamingException, SQLException {
        Map<String, Integer> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT m.name, COUNT(*) as times \n"
                        + "FROM tblOrderDetail o, tblMeal m \n"
                        + "WHERE o.mealID = m.id AND orderID IN \n"
                        + "	(SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?) \n"
                        + "GROUP BY m.name\n"
                        + "HAVING COUNT(*) >=\n"
                        + "	(SELECT MAX(r.times)\n"
                        + "	FROM \n"
                        + "	(SELECT m.name, COUNT(*) as times \n"
                        + "	FROM tblOrderDetail o, tblMeal m \n"
                        + "	WHERE o.mealID = m.id AND orderID IN \n"
                        + "		(SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?) \n"
                        + "	GROUP BY m.name) r)";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);
                stm.setInt(3, year);
                stm.setInt(4, month);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String mealName = rs.getString(1);
                    int times = rs.getInt(2);
                    
                    map.put(mealName, times);
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
    public Map<String, Integer> getDayMenu(int year, int month, int day) throws NamingException, SQLException {
        Map<String, Integer> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT m.name, COUNT(*) as times \n"
                        + "FROM tblOrderDetail o, tblMeal m \n"
                        + "WHERE o.mealID = m.id AND orderID IN \n"
                        + "	(SELECT id FROM tblOrder WHERE date = ?) \n"
                        + "GROUP BY m.name\n"
                        + "HAVING COUNT(*) >=\n"
                        + "	(SELECT MAX(r.times)\n"
                        + "	FROM \n"
                        + "	(SELECT m.name, COUNT(*) as times \n"
                        + "	FROM tblOrderDetail o, tblMeal m \n"
                        + "	WHERE o.mealID = m.id AND orderID IN \n"
                        + "		(SELECT id FROM tblOrder WHERE date = ?) \n"
                        + "	GROUP BY m.name) r)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, year + "-" + month + "-" + day);
                stm.setString(2, year + "-" + month + "-" + day);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String mealName = rs.getString(1);
                    int times = rs.getInt(2);
                    
                    map.put(mealName, times);
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
    
    public Map<Integer, MenuDTO> getMonthMenuDetail(int year, int month) throws NamingException, SQLException {
        Map<Integer, MenuDTO> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT m.name, COUNT(*) as times FROM tblOrderDetail o, tblMeal m WHERE o.mealID = m.id AND orderID IN (SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?) GROUP BY m.name ORDER BY times DESC";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setInt(2, month);

                rs = stm.executeQuery();
                int count = 0;
                while (rs.next()) {
                    String mealName = rs.getString(1);
                    int times = rs.getInt(2);
                    
                    map.put(++count, new MenuDTO(mealName, times));
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
    
    public Map<Integer, MenuDTO> getDayMenuDetail(int year, int month, int day) throws NamingException, SQLException {
        Map<Integer, MenuDTO> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT m.name, COUNT(*) as times FROM tblOrderDetail o, tblMeal m WHERE o.mealID = m.id AND orderID IN (SELECT id FROM tblOrder WHERE date = ?) GROUP BY m.name ORDER BY times DESC";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, year + "-" + month + "-" + day);

                rs = stm.executeQuery();
                int count = 0;
                while (rs.next()) {
                    String mealName = rs.getString(1);
                    int times = rs.getInt(2);
                    
                    map.put(++count, new MenuDTO(mealName, times));
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
}
