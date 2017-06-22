/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tblorder;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class InputTableNumberError implements Serializable{
    private String tableNumberFormatError;
    private String notFoundError;

    public InputTableNumberError() {
    }

    /**
     * @return the tableNumberFormatError
     */
    public String getTableNumberFormatError() {
        return tableNumberFormatError;
    }

    /**
     * @param tableNumberFormatError the tableNumberFormatError to set
     */
    public void setTableNumberFormatError(String tableNumberFormatError) {
        this.tableNumberFormatError = tableNumberFormatError;
    }

    /**
     * @return the notFoundError
     */
    public String getNotFoundError() {
        return notFoundError;
    }

    /**
     * @param notFoundError the notFoundError to set
     */
    public void setNotFoundError(String notFoundError) {
        this.notFoundError = notFoundError;
    }
    
}
