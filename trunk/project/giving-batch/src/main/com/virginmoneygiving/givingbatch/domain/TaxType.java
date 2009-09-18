package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The data object representing TAX details.
 * 
 * @author Tarun Adiwal
 */
public class TaxType implements Serializable {

    /** serial version uid. */
    private static final long serialVersionUID = 1L;

    /** Amount. * */
    private BigDecimal amount;

    /** Code. * */
    private String code;

    /** Rate. * */
    private BigDecimal rate;

    /** Status. * */
    private String status;

    /**
     * Gets the amount.
     * 
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     * 
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the rate.
     * 
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the rate.
     * 
     * @param rate the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the serial version uid.
     * 
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
