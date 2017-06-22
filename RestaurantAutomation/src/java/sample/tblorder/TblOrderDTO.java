/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import sample.tblorderdetail.TblOrderDetailDTO;

/**
 *
 * @author ahhun
 */
public class TblOrderDTO implements Serializable{
    private String id;
    private int tableNumber;
    private String waiterID;
    private String customerName;
    private Date date;
    private Time arrivingTime;
    private Time leavingTime;
    private List<TblOrderDetailDTO> orderDetails;

    public TblOrderDTO() {
    }
    
    public TblOrderDTO(String id) {
        this.id = id;
    }

    public TblOrderDTO(String id, int tableNumber, String waiterID, String customerName, Date date, Time arrivingTime, Time leavingTime, List<TblOrderDetailDTO> orderDetails) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.waiterID = waiterID;
        this.customerName = customerName;
        this.date = date;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.orderDetails = orderDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(String waiterID) {
        this.waiterID = waiterID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Time arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public Time getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Time leavingTime) {
        this.leavingTime = leavingTime;
    }

    public List<TblOrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<TblOrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
}
