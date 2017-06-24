/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorderdetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;

/**
 *
 * @author Turtle
 */
public class OrderDetail implements Serializable {
    private String orderId;
    private int tableNumber
    private int no;
    private String mealName;
    private String mealUnit;
    private BigDecimal quantity;
    private String category;
    private Time takenTime;
    private String status;    

    public OrderDetail(String orderId, int tableNumber, int no, String mealName, String mealUnit, BigDecimal quantity, String category, Time takenTime, String status) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.no = no;
        this.mealName = mealName;
        this.mealUnit = mealUnit;
        this.quantity = quantity;
        this.category = category;
        this.takenTime = takenTime;
        this.status = status;
    }
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealUnit() {
        return mealUnit;
    }

    public void setMealUnit(String mealUnit) {
        this.mealUnit = mealUnit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Time getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Time takenTime) {
        this.takenTime = takenTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
