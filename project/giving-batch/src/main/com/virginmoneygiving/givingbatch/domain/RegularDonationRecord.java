package com.virginmoneygiving.givingbatch.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * The Class hold the information regarding the regular donation.
 * 
 * @author taruna
 */
public class RegularDonationRecord implements Serializable{

    /** Serial version uid. */
    private static final long serialVersionUID = 1L;
    
    /** Next Collection Date. */
    private Date nextCollectionDate = null;
    
    /** Name on the Card. */
    private String nameOnCard = null;
    
    /** Payment amount. */
    private BigDecimal paymentAmount = null;
    
    /** Card Type. */
    private String cardType = null;

    /**
     * Gets the next collection date.
     * 
     * @return the nextCollectionDate
     */
    public Date getNextCollectionDate() {
        return nextCollectionDate;
    }

    /**
     * Sets the next collection date.
     * 
     * @param nextCollectionDate the nextCollectionDate to set
     */
    public void setNextCollectionDate(Date nextCollectionDate) {
        this.nextCollectionDate = nextCollectionDate;
    }

    /**
     * Gets the name on card.
     * 
     * @return the nameOnCard
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * Sets the name on card.
     * 
     * @param nameOnCard the nameOnCard to set
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    /**
     * Gets the payment amount.
     * 
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payment amount.
     * 
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the card type.
     * 
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the card type.
     * 
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

   
    
    

}
