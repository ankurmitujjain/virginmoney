
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Purchase"/>
 *     &lt;enumeration value="Refund"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionTypeEnum")
@XmlEnum
public enum TransactionTypeEnum {

    @XmlEnumValue("Purchase")
    PURCHASE("Purchase"),
    @XmlEnumValue("Refund")
    REFUND("Refund");
    private final String value;

    TransactionTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionTypeEnum fromValue(String v) {
        for (TransactionTypeEnum c: TransactionTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
