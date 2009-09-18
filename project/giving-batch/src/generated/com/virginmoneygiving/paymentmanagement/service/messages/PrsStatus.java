
package com.virginmoneygiving.paymentmanagement.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrsStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrsStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WEBPAYINIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrsStatus", namespace = "http://www.virginmoneygiving.com/type/payment-management/operations/")
@XmlEnum
public enum PrsStatus {

    WEBPAYINIT;

    public String value() {
        return name();
    }

    public static PrsStatus fromValue(String v) {
        return valueOf(v);
    }

}
