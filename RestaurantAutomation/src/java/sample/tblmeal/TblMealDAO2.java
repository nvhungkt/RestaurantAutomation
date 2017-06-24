/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblmeal;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author VT
 */
public class TblMealDAO2 implements Serializable {
    
    private Connection con;
    private PreparedStatement stm;
    private CallableStatement cstm;
    private ResultSet rs;
    
    private List<TblMealDTO2> list;

    public List<TblMealDTO2> getList() {
        return list;
    }
    
    public void loadAllMeals() throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call loadAllMeals()}";
                
                cstm = con.prepareCall(sql);
                
                boolean hasResult = cstm.execute();
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        if (list == null)
                            list = new ArrayList<>();
                        
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String unit = rs.getString("unit");
                        BigDecimal price = rs.getBigDecimal("price");
                        String cateID = rs.getString("cateID");
                        
                        TblMealDTO2 dto = new TblMealDTO2(id, name, unit, price, cateID);
                        list.add(dto);
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
    }
    
    public void addMeal(String name, String unit, BigDecimal price, String cateID) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call addMeal(?, ?, ?, ?)}";
                
                cstm = con.prepareCall(sql);
                cstm.setString(1, name);
                cstm.setString(2, unit);
                cstm.setBigDecimal(3, price);
                cstm.setString(4, cateID);
                
                cstm.execute();
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
    }
    
    public void updateMeal(String id, String name, String unit, BigDecimal price, String cateID) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call updateMeal(?, ?, ?, ?, ?)}";
                
                cstm = con.prepareCall(sql);
                cstm.setString(1, id);
                cstm.setString(2, name);
                cstm.setString(3, unit);
                cstm.setBigDecimal(4, price);
                cstm.setString(5, cateID);
                
                cstm.execute();
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
    }
    
    public void removeMeal(String id) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call removeMeal(?)}";
                
                cstm = con.prepareCall(sql);
                cstm.setString(1, id);
                
                cstm.execute();
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
    }
    
    public void updatePrice(String id, BigDecimal price) throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call updatePrice(?, ?)}";
                
                cstm = con.prepareCall(sql);
                cstm.setString(1, id);
                cstm.setBigDecimal(2, price);
                
                cstm.execute();
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
    }
}
