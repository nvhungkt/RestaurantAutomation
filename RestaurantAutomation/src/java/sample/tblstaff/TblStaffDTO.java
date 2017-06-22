/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblstaff;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class TblStaffDTO implements Serializable{
    private String id;
    private String password;
    private String name;
    private String role;    

    public TblStaffDTO() {
    }

    public TblStaffDTO(String id, String password, String name, String role) {
        this.id = id;
        this.password = password;
        this.name = name;        
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the isActive
     */
   
    
}
