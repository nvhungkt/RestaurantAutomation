/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Administrator
 */
public class TblOrderDTO implements Serializable{
    private String id;
    private int tableNumber;
    private String waiterID;
    private String customerName;
    private Date date;
    private Time arriveTime;
    private Time leaveTime;
    private String status;

    public TblOrderDTO() {
    }

    public TblOrderDTO(String id, int tableNumber, String waiterID, String customerName, Date date, Time arriveTime, Time leaveTime, String status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.waiterID = waiterID;
        this.customerName = customerName;
        this.date = date;
        this.arriveTime = arriveTime;
        this.leaveTime = leaveTime;
        this.status = status;
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
     * @return the tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * @param tableNumber the tableNumber to set
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * @return the waiterID
     */
    public String getWaiterID() {
        return waiterID;
    }

    /**
     * @param waiterID the waiterID to set
     */
    public void setWaiterID(String waiterID) {
        this.waiterID = waiterID;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the arriveTime
     */
    public Time getArriveTime() {
        return arriveTime;
    }

    /**
     * @param arriveTime the arriveTime to set
     */
    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * @return the leaveTime
     */
    public Time getLeaveTime() {
        return leaveTime;
    }

    /**
     * @param leaveTime the leaveTime to set
     */
    public void setLeaveTime(Time leaveTime) {
        this.leaveTime = leaveTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
