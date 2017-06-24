/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblmeal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author VT
 */
public class TblMealDTO2 implements Serializable {
    private String id;
    private String name;
    private String unit;
    private BigDecimal price;
    private String cateID;

    public TblMealDTO2() {
    }

    public TblMealDTO2(String id, String name, String unit, BigDecimal price, String cateID) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.cateID = cateID;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the cateID
     */
    public String getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
}
