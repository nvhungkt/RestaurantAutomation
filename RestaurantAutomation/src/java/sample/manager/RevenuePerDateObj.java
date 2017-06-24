/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager;

import java.io.Serializable;

/**
 *
 * @author VT
 */
public class RevenuePerDateObj implements Serializable {
    private String date;
    private String revenue;

    public RevenuePerDateObj() {
    }

    public RevenuePerDateObj(String date, String revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the revenue
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
