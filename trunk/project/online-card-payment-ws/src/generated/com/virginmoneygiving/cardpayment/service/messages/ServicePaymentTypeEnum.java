
package com.virginmoneygiving.cardpayment.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServicePaymentTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServicePaymentTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REGISTRATION_FEE"/>
 *     &lt;enumeration value="EVENT_FEE"/>
 *     &lt;enumeration value="REGULAR"/>
 *     &lt;enumeration value="PAYMENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServicePaymentTypeEnum", namespace = "http://www.virginmoneygiving.com/type/online-card-payment/card-payment")
@XmlEnum
public enum ServicePaymentTypeEnum {

    REGISTRATION_FEE,
    EVENT_FEE,
    REGULAR,
    PAYMENT;

    public String value() {
        return name();
    }

    public static ServicePaymentTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
