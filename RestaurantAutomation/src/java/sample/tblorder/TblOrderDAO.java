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
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */
public class TblOrderDAO implements Serializable{
    private List<TblOrderDTO> listOrder = null;

    public List<TblOrderDTO> getListOrder() {
        return listOrder;
    }
    
    public boolean loadOrderByTableNubmer(int tableNumber) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if(con != null) {
                String status = "";
                String sql = "Select * from tblOrder Where tableNumber = ? and status = ?";
                stm = con.prepareStatement(sql);
                
                stm.setInt(1, tableNumber);
                stm.setString(2, status);
                
                rs = stm.executeQuery();
                if(rs.next()) {
                    String id = rs.getString("id");
                    // already have tableNumber
                    String waiterID = rs.getString("waiterID");
                    String customerName = rs.getString("customerName");
                    Date date = rs.getDate("date");
                    Time arriveTime = rs.getTime("arriveTime");
                    Time leaveTime = rs.getTime("leaveTime");
                    // already have status
                    TblOrderDTO dto = 
                    new TblOrderDTO(id, tableNumber, waiterID, customerName, date, arriveTime, leaveTime, status);
                    if(listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    listOrder.add(dto);
                    return true;
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
        
        return false;
    }
}
