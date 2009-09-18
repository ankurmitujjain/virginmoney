
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentReceiveSelection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentReceiveSelection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="webDonation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="webRegularDonation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="webEventRegistrationFee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cashBack" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="selectAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentReceiveSelection", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/", propOrder = {
    "webDonation",
    "webRegularDonation",
    "webEventRegistrationFee",
    "cashBack",
    "selectAll"
})
public class PaymentReceiveSelection {

    protected boolean webDonation;
    protected boolean webRegularDonation;
    protected boolean webEventRegistrationFee;
    protected boolean cashBack;
    protected boolean selectAll;

    /**
     * Gets the value of the webDonation property.
     * 
     */
    public boolean isWebDonation() {
        return webDonation;
    }

    /**
     * Sets the value of the webDonation property.
     * 
     */
    public void setWebDonation(boolean value) {
        this.webDonation = value;
    }

    /**
     * Gets the value of the webRegularDonation property.
     * 
     */
    public boolean isWebRegularDonation() {
        return webRegularDonation;
    }

    /**
     * Sets the value of the webRegularDonation property.
     * 
     */
    public void setWebRegularDonation(boolean value) {
        this.webRegularDonation = value;
    }

    /**
     * Gets the value of the webEventRegistrationFee property.
     * 
     */
    public boolean isWebEventRegistrationFee() {
        return webEventRegistrationFee;
    }

    /**
     * Sets the value of the webEventRegistrationFee property.
     * 
     */
    public void setWebEventRegistrationFee(boolean value) {
        this.webEventRegistrationFee = value;
    }

    /**
     * Gets the value of the cashBack property.
     * 
     */
    public boolean isCashBack() {
        return cashBack;
    }

    /**
     * Sets the value of the cashBack property.
     * 
     */
    public void setCashBack(boolean value) {
        this.cashBack = value;
    }

    /**
     * Gets the value of the selectAll property.
     * 
     */
    public boolean isSelectAll() {
        return selectAll;
    }

    /**
     * Sets the value of the selectAll property.
     * 
     */
    public void setSelectAll(boolean value) {
        this.selectAll = value;
    }

}
