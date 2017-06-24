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
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtilities;
import sample.utils.DateFormatter;

/**
 *
 * @author VT
 */
public class ManagerDAO implements Serializable {
    private Connection con;
    private PreparedStatement stm;
    private CallableStatement cstm;
    private ResultSet rs;
    
    public double getTotalRevenue() throws NamingException, SQLException {
        double revenue = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getTotalRevenue()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    if (rs.next())
                        revenue = rs.getBigDecimal(1).doubleValue();
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return revenue;
    }
    
    public List<RevenuePerDateObj> loadRevenuePerDate() throws NamingException, SQLException {
        List<RevenuePerDateObj> list = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getRevenuePerDay()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                while (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        String date = DateFormatter.getDateString(rs.getDate(2));
                        String revenue = String.format("%.2f", rs.getBigDecimal(1).floatValue());
                        
                        RevenuePerDateObj obj = new RevenuePerDateObj(date, revenue);
                        if (list == null)
                            list = new ArrayList<>();
                        list.add(obj);
                    }
                    
                    hasResult = cstm.getMoreResults();
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return list;
    }
    
    public List<String> getMostPopularItem() throws NamingException, SQLException {
        List<String> list = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getMostPopularItem()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        String item = rs.getString(1) + ": " + rs.getInt(2) + " time(s)";
                        if (list == null)
                            list = new ArrayList<>();
                        list.add(item);
                    }
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return list;
    }
    
    public List<String> getBusboyEfficiency() throws NamingException, SQLException {
        List<String> list = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getBusboyEfficiency()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        String item = rs.getString(1) + "-" + rs.getString(2) + ": cleaned " + rs.getInt(3) + " table(s)";
                        if (list == null)
                            list = new ArrayList<>();
                        list.add(item);
                    }
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return list;
    }
    
    public List<String> getWaiterEfficiency() throws NamingException, SQLException {
        List<String> list = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getWaiterEfficiency()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        String item = rs.getString(1) + "-" + rs.getString(2) + ": served " + rs.getInt(3) + " order(s)";
                        if (list == null)
                            list = new ArrayList<>();
                        list.add(item);
                    }
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return list;
    }
    
    public List<String> getKitchenEfficiency() throws NamingException, SQLException {
        List<String> list = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getKitchenEfficiency()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        String item = rs.getString(1) + "-" + rs.getString(2) + ": cooked " + rs.getInt(3) + " meal(s)";
                        if (list == null)
                            list = new ArrayList<>();
                        list.add(item);
                    }
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return list;
    }
    
    public int getAvgTurnAroundTime() throws NamingException, SQLException {
        int turnAround = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getAvgTurnAroundTime()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    if (rs.next())
                        turnAround = rs.getInt(1);
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return turnAround;
    }
    
    public int getAvgPreparationTime() throws NamingException, SQLException {
        int preparationTime = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call getAvgPreparationTime()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    if (rs.next())
                        preparationTime = rs.getInt(1);
                }
            }//end if con
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (cstm != null)
                cstm.close();
            if (con != null)
                con.close();
        }
        return preparationTime;
    }
}
