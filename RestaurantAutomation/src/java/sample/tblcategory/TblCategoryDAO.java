/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblcategory;

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
public class TblCategoryDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement stm;
    private CallableStatement cstm;
    private ResultSet rs;
    
    private List<TblCategoryDTO> list;

    public List<TblCategoryDTO> getList() {
        return list;
    }
    
    public void loadCategory() throws NamingException, SQLException {
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "{call loadCategory()}";
                
                cstm = con.prepareCall(sql);
                boolean hasResult = cstm.execute();
                
                if (hasResult) {
                    rs = cstm.getResultSet();
                    
                    while (rs.next()) {
                        if (list == null)
                            list = new ArrayList<>();
                        
                        String id = rs.getString(1);
                        String name = rs.getString(2);
                        
                        TblCategoryDTO dto = new TblCategoryDTO(id, name);
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
}
