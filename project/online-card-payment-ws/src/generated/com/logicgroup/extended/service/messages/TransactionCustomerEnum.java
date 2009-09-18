
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionCustomerEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionCustomerEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotPresent"/>
 *     &lt;enumeration value="Present"/>
 *     &lt;enumeration value="Internet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionCustomerEnum")
@XmlEnum
public enum TransactionCustomerEnum {

    @XmlEnumValue("NotPresent")
    NOT_PRESENT("NotPresent"),
    @XmlEnumValue("Present")
    PRESENT("Present"),
    @XmlEnumValue("Internet")
    INTERNET("Internet");
    private final String value;

    TransactionCustomerEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionCustomerEnum fromValue(String v) {
        for (TransactionCustomerEnum c: TransactionCustomerEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
