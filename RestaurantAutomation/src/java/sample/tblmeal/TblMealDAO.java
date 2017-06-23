/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblmeal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */
public class TblMealDAO implements Serializable{
    private List<TblMealDTO> listMeal = null;

    public List<TblMealDTO> getListMeal() {
        return listMeal;
    }
    
    public void loadAvailableMeal() throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if(con != null) {
                String sql = "select m.id, m.name, m.unit, p.price, c.name as category " +
                             "from tblMeal m, tblPrice p, tblCategory c " +
                             "where m.isAvailable = 'true' and m.id = p.mealID and m.cateID = c.id";
                stm = con.createStatement();
                
                
                rs = stm.executeQuery(sql);
                while(rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
//                    BigDecimal price = rs.getBigDecimal("price");
                    int price = rs.getInt("price");
                    String cate = rs.getString("category");
                    TblMealDTO dto = new TblMealDTO(id, name, unit, cate, price);
                    if(listMeal == null) {
                        listMeal = new ArrayList<>();
                    }
                    listMeal.add(dto);
                }
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
    
    public TblMealDTO loadMeal(String mealID) 
            throws SQLException, NamingException {
        Connection con =null;
        PreparedStatement stm = null;        
        ResultSet rs = null;
        
        try {            
            con = DBUtilities.makeConnection();    
            if(con != null) {
                String sql = "select m.id, m.name, m.unit, p.price, c.name " +
                             "from tblMeal m, tblPrice p, tblCategory " +
                             "where m.id = p.mealID and m.cateID = c.id and m.id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mealID);
                
                rs = stm.executeQuery(sql);
                if(rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    int price = rs.getInt("price");
                    String cate = rs.getString("category");
                    TblMealDTO dto = new TblMealDTO(id, name, unit, cate, price);
                    return dto;
                }
            }
        }
        finally {
            if(rs != null) {
                rs.close();
            }
            if(stm!=null) {
                stm.close();
            }
            if(con!=null) {
                con.close();
            }
        }
        return null;
    }
}
