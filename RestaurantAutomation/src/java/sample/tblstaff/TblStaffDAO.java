/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblstaff;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.utils.DBUtilities;

/**
 *
 * @author Administrator
 */
public class TblStaffDAO implements Serializable{
    public TblStaffDTO login(String id, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblStaffDTO staff = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "SELECT * FROM tblStaff WHERE id=? AND password=?";
            stm=con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");                
                String role = rs.getString("role");
                boolean isActive = rs.getBoolean("isActive");
                if(isActive) {
                    staff = new TblStaffDTO(id, password, name, role);
                }
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
        return staff;
    }
}
