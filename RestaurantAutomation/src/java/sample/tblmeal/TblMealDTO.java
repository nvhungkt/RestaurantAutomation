/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblmeal;

import java.io.Serializable;

/**
 *
 * @author ahhun
 */
public class TblMealDTO implements Serializable{
    private String id;
    private String name;
    private String unit;
    private String cate;
    private float price;

    public TblMealDTO() {
    }

    public TblMealDTO(String id, String name, String unit, String cate, float price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.cate = cate;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
