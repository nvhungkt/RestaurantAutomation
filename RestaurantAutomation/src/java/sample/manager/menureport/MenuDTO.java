/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.manager.menureport;

import java.io.Serializable;

/**
 *
 * @author HT
 */
public class MenuDTO implements Serializable {
    private String mealName;
    private int times;

    public MenuDTO(String mealName, int times) {
        this.mealName = mealName;
        this.times = times;
    }

    public MenuDTO() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
    
    
}
