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
public class tblOrderDetailDTO implements Serializable {
    private String orderId;
    private String no;
    private String mealId;
    private BigDecimal quantity;
    private Time takenTime;
    private Time readyTime;
    private String status;
    private String kitchenId;

    public tblOrderDetailDTO(String orderId, String no, String mealId, BigDecimal quantity, Time takenTime, Time readyTime, String status, String kitchenId) {
        this.orderId = orderId;
        this.no = no;
        this.mealId = mealId;
        this.quantity = quantity;
        this.takenTime = takenTime;
        this.readyTime = readyTime;
        this.status = status;
        this.kitchenId = kitchenId;
    }

    public tblOrderDetailDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Time getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Time takenTime) {
        this.takenTime = takenTime;
    }

    public Time getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Time readyTime) {
        this.readyTime = readyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(String kitchenId) {
        this.kitchenId = kitchenId;
    }
            
}
