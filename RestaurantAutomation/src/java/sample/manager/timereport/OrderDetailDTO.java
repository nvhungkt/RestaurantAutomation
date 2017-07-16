/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.timereport;

import java.io.Serializable;
/**
 *
 * @author ahhun
 */
public class OrderDetailDTO implements Serializable{
    private String orderID;
    private String mealName;
    private int quantity;
    private String cookName;
    private int avgTime;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String mealName, int quantity, String cookName, int avgTime) {
        this.orderID = orderID;
        this.mealName = mealName;
        this.quantity = quantity;
        this.cookName = cookName;
        this.avgTime = avgTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

        
}
