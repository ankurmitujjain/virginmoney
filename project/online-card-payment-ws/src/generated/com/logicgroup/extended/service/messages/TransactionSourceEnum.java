
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionSourceEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionSourceEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Keyed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionSourceEnum")
@XmlEnum
public enum TransactionSourceEnum {

    @XmlEnumValue("Keyed")
    KEYED("Keyed");
    private final String value;

    TransactionSourceEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionSourceEnum fromValue(String v) {
        for (TransactionSourceEnum c: TransactionSourceEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
