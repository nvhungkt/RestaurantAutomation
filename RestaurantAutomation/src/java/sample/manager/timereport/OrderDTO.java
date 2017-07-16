/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.timereport;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author ahhun
 */
public class OrderDTO implements Serializable{
    private String id;
    private int tableNumber;
    private Time arrivingTime;
    private Time leavingTime;
    private int avgTime;

    public OrderDTO(String id, int tableNumber, Time arrivingTime, Time leavingTime, int avgTime) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.avgTime = avgTime;
    }

    public OrderDTO() {
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

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }
    
    
}
