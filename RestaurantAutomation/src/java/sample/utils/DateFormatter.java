/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author VT
 */
public class DateFormatter implements Serializable {
    
    public static String getCurrentDateString() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        return fm.format(date);
    }
    
    public static Timestamp getCurrentSQLTime() {
        java.util.Date date = new java.util.Date();
        Timestamp time = new Timestamp(date.getTime());
        return time;
    }
    
    public static String getDateString(Date date) {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        return fm.format(date);
    }
}
