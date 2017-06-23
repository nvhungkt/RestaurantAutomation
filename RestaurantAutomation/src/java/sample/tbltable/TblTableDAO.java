/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbltable;

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

/**
 *
 * @author Administrator
 */
public class TblTableDAO implements Serializable{
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    public List<TblTableDTO> getTables() throws NamingException, SQLException {
        List<TblTableDTO> tables = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT * FROM tblTable WHERE isActive = ?");
                stm.setBoolean(1, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int number = rs.getInt("number");
                    int capacity = rs.getInt("capacity");
                    String status = rs.getString("status");
                    if (tables == null) tables = new ArrayList();
                    tables.add(new TblTableDTO(number, capacity, status));
                }
            }
        } finally {
            
        }
        return tables;
    }
    
    public void updateTable(int number, int capacity) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call updateTable(?, ?)}";
                CallableStatement cstm = con.prepareCall(sql);
                
                cstm.setInt(1, number);
                cstm.setInt(2, capacity);
                
                cstm.execute();
            }
        } finally {
           if (con != null)
               con.close();
        }
    }
    
    //actually just deactive this table
    public void removeTable(int number) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call removeTable(?)}";
                CallableStatement cstm = con.prepareCall(sql);
                
                cstm.setInt(1, number);
                
                cstm.execute();
            }
        } finally {
           if (con != null)
               con.close();
        }
    }
    
    public boolean checkTableExist(int number) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblTable WHERE number = ? AND isActive = 1";
                stm = con.prepareStatement(sql);
                stm.setInt(1, number);
                
                rs = stm.executeQuery();
                if (rs.next())
                    return true;
            }
        } finally {
           if (con != null)
               con.close();
        }
        return false;
    }
}
