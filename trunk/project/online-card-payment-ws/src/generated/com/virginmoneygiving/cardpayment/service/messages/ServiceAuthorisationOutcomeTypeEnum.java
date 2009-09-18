
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceAuthorisationOutcomeTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceAuthorisationOutcomeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="AUTHENTICATION_REQUIRED"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServiceAuthorisationOutcomeTypeEnum", namespace = "http://www.virginmoneygiving.com/type/online-card-payment/card-payment")
@XmlEnum
public enum ServiceAuthorisationOutcomeTypeEnum {

    SUCCESS,
    AUTHENTICATION_REQUIRED,
    ERROR;

    public String value() {
        return name();
    }

    public static ServiceAuthorisationOutcomeTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
