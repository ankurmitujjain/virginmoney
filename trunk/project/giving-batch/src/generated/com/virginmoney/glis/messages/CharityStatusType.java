
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharityStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CharityStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Live"/>
 *     &lt;enumeration value="Closed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CharityStatusType")
@XmlEnum
public enum CharityStatusType {

    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Live")
    LIVE("Live"),
    @XmlEnumValue("Closed")
    CLOSED("Closed");
    private final String value;

    CharityStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CharityStatusType fromValue(String v) {
        for (CharityStatusType c: CharityStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
