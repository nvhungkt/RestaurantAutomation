/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.tblstaff.TblStaffDTO;
import sample.utils.DBUtilities;

/**
 *
 * @author HT
 */
public class StaffDAO implements Serializable {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    //staffRole: 0-busboy; 1-waiter; 2-cook
    
    public List<String> getYearRange(int staffRole) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[3];
                sql[0] = "SELECT DISTINCT DATEPART(YEAR, time) FROM tblClean";
                sql[1] = "SELECT DISTINCT DATEPART(YEAR, date) FROM tblOrder";
                sql[2] = "SELECT DISTINCT DATEPART(YEAR, date) "
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail)";

                stm = con.prepareStatement(sql[staffRole]);

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

    public List<String> getMonthsOf(int year, int staffRole) throws NamingException, SQLException {
        List<String> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[3];
                sql[0] = "SELECT DISTINCT DATEPART(MONTH, time) FROM tblClean WHERE DATEPART(YEAR, time) = ?";
                sql[1] = "SELECT DISTINCT DATEPART(MONTH, date) FROM tblOrder WHERE DATEPART(YEAR, date) = ?";
                sql[2] = "SELECT DISTINCT DATEPART(MONTH, date) "
                        + "FROM tblOrder WHERE id IN "
                        + "(SELECT DISTINCT orderID FROM tblOrderDetail) AND DATEPART(YEAR, date) = ?";
                
                stm = con.prepareStatement(sql[staffRole]);
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
    
    public List<TblStaffDTO> getAllStaffOf(int staffRole) throws NamingException, SQLException {
        List<TblStaffDTO> list = new ArrayList<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[3];
                sql[0] = "SELECT * FROM tblStaff WHERE role = 'bus boy' AND isActive = 1";
                sql[1] = "SELECT * FROM tblStaff WHERE role = 'waiter' AND isActive = 1";
                sql[2] = "SELECT * FROM tblStaff WHERE role = 'cook' AND isActive = 1";
                
                stm = con.prepareStatement(sql[staffRole]);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(3);
                    
                    TblStaffDTO dto = new TblStaffDTO(id, "", name, "");
                    list.add(dto);
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
    
    public Map<Integer, TblStaffDTO> getMonthlyStaffEfficiency(int year, int month, int staffRole) throws NamingException, SQLException {
        Map<Integer, TblStaffDTO> map = new HashMap<>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql[] = new String[3];
                sql[0] = "SELECT s.id, s.name, COUNT(*) as times FROM tblClean c, tblStaff s WHERE DATEPART(Year, time) = ? AND DATEPART(MONTH, time) = ? AND c.busboyID = s.id GROUP BY s.id, s.name ORDER BY times DESC";
                sql[1] = "SELECT s.id, s.name, COUNT(*) as times FROM tblOrder o, tblStaff s WHERE DATEPART(Year, date) = ? AND DATEPART(MONTH, date) = ? AND o.waiterID = s.id GROUP BY s.id, s.name ORDER BY times DESC";
                sql[2] = "SELECT o.cookID, s.name, COUNT(*) as times FROM tblOrderDetail o, tblStaff s WHERE orderID IN "
                        + "(SELECT id FROM tblOrder WHERE DATEPART(YEAR, date) = ? AND DATEPART(MONTH, date) = ?) "
                        + "AND o.cookID = s.id "
                        + "GROUP BY o.cookID, s.name "
                        + "ORDER BY times DESC";
                
                stm = con.prepareStatement(sql[staffRole]);
                stm.setInt(1, year);
                stm.setInt(2, month);

                rs = stm.executeQuery();
                int stt = 0;
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    
                    int times = rs.getInt(3);
                    TblStaffDTO dto = new TblStaffDTO(id, "", name, times + "");
                    
                    map.put(++stt, dto);
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
