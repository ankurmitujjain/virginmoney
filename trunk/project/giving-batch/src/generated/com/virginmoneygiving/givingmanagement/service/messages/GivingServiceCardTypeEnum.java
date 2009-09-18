
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GivingServiceCardTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GivingServiceCardTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VISA"/>
 *     &lt;enumeration value="VISADEBIT"/>
 *     &lt;enumeration value="MASTERCARD"/>
 *     &lt;enumeration value="AMEX"/>
 *     &lt;enumeration value="MAESTRO"/>
 *     &lt;enumeration value="SOLO"/>
 *     &lt;enumeration value="VISAELECTRON"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GivingServiceCardTypeEnum", namespace = "http://www.virginmoneygiving.com/type/giving-management/card-payment/")
@XmlEnum
public enum GivingServiceCardTypeEnum {

    VISA,
    VISADEBIT,
    MASTERCARD,
    AMEX,
    MAESTRO,
    SOLO,
    VISAELECTRON;

    public String value() {
        return name();
    }

    public static GivingServiceCardTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
