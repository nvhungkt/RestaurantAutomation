/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorderdetail;

import java.io.Serializable;
import java.sql.Time;
import sample.tblmeal.TblMealDTO;

/**
 *
 * @author ahhun
 */
public class TblOrderDetailDTO implements Serializable{
    private int no;
    private TblMealDTO meal;
    private int quantity;
    private Time takenTime;
    private Time readyTime;
    private String status;
    private String cookID;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(int no, TblMealDTO meal, int quantity, Time takenTime, Time readyTime, String status, String cookID) {
        this.no = no;
        this.meal = meal;
        this.quantity = quantity;
        this.takenTime = takenTime;
        this.readyTime = readyTime;
        this.status = status;
        this.cookID = cookID;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public TblMealDTO getMeal() {
        return meal;
    }

    public void setMeal(TblMealDTO meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }
    
}
