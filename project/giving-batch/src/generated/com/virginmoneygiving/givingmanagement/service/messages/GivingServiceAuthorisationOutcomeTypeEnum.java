
package com.virginmoneygiving.givingmanagement.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GivingServiceAuthorisationOutcomeTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GivingServiceAuthorisationOutcomeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="AUTHENTICATION_REQUIRED"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GivingServiceAuthorisationOutcomeTypeEnum", namespace = "http://www.virginmoneygiving.com/type/giving-management/card-payment/")
@XmlEnum
public enum GivingServiceAuthorisationOutcomeTypeEnum {

    SUCCESS,
    AUTHENTICATION_REQUIRED,
    ERROR;

    public String value() {
        return name();
    }

    public static GivingServiceAuthorisationOutcomeTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
