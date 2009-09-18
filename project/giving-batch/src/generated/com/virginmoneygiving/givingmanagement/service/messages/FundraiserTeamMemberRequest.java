
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.virginmoneygiving.com/type/header/}MessageHeader"/>
 *         &lt;element name="processFlow" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceFundraiserTeamMember" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}ServiceFundraiserTeamMember"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "processFlow",
    "serviceFundraiserTeamMember"
})
@XmlRootElement(name = "fundraiserTeamMemberRequest", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/")
public class FundraiserTeamMemberRequest {

    @XmlElement(name = "Header", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected MessageHeader header;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected String processFlow;
    @XmlElement(namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", required = true)
    protected ServiceFundraiserTeamMember serviceFundraiserTeamMember;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setHeader(MessageHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the processFlow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessFlow() {
        return processFlow;
    }

    /**
     * Sets the value of the processFlow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessFlow(String value) {
        this.processFlow = value;
    }

    /**
     * Gets the value of the serviceFundraiserTeamMember property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceFundraiserTeamMember }
     *     
     */
    public ServiceFundraiserTeamMember getServiceFundraiserTeamMember() {
        return serviceFundraiserTeamMember;
    }

    /**
     * Sets the value of the serviceFundraiserTeamMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceFundraiserTeamMember }
     *     
     */
    public void setServiceFundraiserTeamMember(ServiceFundraiserTeamMember value) {
        this.serviceFundraiserTeamMember = value;
    }

}
