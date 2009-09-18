
package com.virginmoneygiving.givingmanagement.service.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServiceFundraiserTeamMember complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceFundraiserTeamMember">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="teamId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="teamName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="teamURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loggedUserForeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loggedUserSurName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loggedUserEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddTeamMemberList" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}TeamMemberDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="removeTeamMemberList" type="{http://www.virginmoneygiving.com/type/giving-management/fundraiser/}TeamMemberDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFundraiserTeamMember", namespace = "http://www.virginmoneygiving.com/type/giving-management/fundraiser/", propOrder = {
    "teamId",
    "teamName",
    "teamURL",
    "loggedUserForeName",
    "loggedUserSurName",
    "loggedUserEmailAddress",
    "addTeamMemberList",
    "removeTeamMemberList",
    "startDate",
    "endDate"
})
public class ServiceFundraiserTeamMember {

    protected long teamId;
    @XmlElement(required = true)
    protected String teamName;
    @XmlElement(required = true)
    protected String teamURL;
    @XmlElement(required = true)
    protected String loggedUserForeName;
    @XmlElement(required = true)
    protected String loggedUserSurName;
    @XmlElement(required = true)
    protected String loggedUserEmailAddress;
    @XmlElement(name = "AddTeamMemberList", nillable = true)
    protected List<TeamMemberDetail> addTeamMemberList;
    @XmlElement(nillable = true)
    protected List<TeamMemberDetail> removeTeamMemberList;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;

    /**
     * Gets the value of the teamId property.
     * 
     */
    public long getTeamId() {
        return teamId;
    }

    /**
     * Sets the value of the teamId property.
     * 
     */
    public void setTeamId(long value) {
        this.teamId = value;
    }

    /**
     * Gets the value of the teamName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the value of the teamName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamName(String value) {
        this.teamName = value;
    }

    /**
     * Gets the value of the teamURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamURL() {
        return teamURL;
    }

    /**
     * Sets the value of the teamURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamURL(String value) {
        this.teamURL = value;
    }

    /**
     * Gets the value of the loggedUserForeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedUserForeName() {
        return loggedUserForeName;
    }

    /**
     * Sets the value of the loggedUserForeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedUserForeName(String value) {
        this.loggedUserForeName = value;
    }

    /**
     * Gets the value of the loggedUserSurName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedUserSurName() {
        return loggedUserSurName;
    }

    /**
     * Sets the value of the loggedUserSurName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedUserSurName(String value) {
        this.loggedUserSurName = value;
    }

    /**
     * Gets the value of the loggedUserEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedUserEmailAddress() {
        return loggedUserEmailAddress;
    }

    /**
     * Sets the value of the loggedUserEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedUserEmailAddress(String value) {
        this.loggedUserEmailAddress = value;
    }

    /**
     * Gets the value of the addTeamMemberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addTeamMemberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddTeamMemberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TeamMemberDetail }
     * 
     * 
     */
    public List<TeamMemberDetail> getAddTeamMemberList() {
        if (addTeamMemberList == null) {
            addTeamMemberList = new ArrayList<TeamMemberDetail>();
        }
        return this.addTeamMemberList;
    }

    /**
     * Gets the value of the removeTeamMemberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the removeTeamMemberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemoveTeamMemberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TeamMemberDetail }
     * 
     * 
     */
    public List<TeamMemberDetail> getRemoveTeamMemberList() {
        if (removeTeamMemberList == null) {
            removeTeamMemberList = new ArrayList<TeamMemberDetail>();
        }
        return this.removeTeamMemberList;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

}
