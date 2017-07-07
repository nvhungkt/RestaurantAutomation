/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager;

/**
 *
 * @author HT
 */
public class OrderDetailReportDTO {
    private String orderID;
    private String mealName;
    private int quantity;
    private float price;

    public OrderDetailReportDTO() {
    }

    public OrderDetailReportDTO(String orderID, String mealName, int quantity, float price) {
        this.orderID = orderID;
        this.mealName = mealName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the mealName
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * @param mealName the mealName to set
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
