/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbltable;

/**
 *
 * @author ahhun
 */
public class TblTableDTO {
    private int number;
    private int capacity;
    private String status;

    public TblTableDTO() {
    }

    public TblTableDTO(int number, int capacity, String status) {
        this.number = number;
        this.capacity = capacity;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
