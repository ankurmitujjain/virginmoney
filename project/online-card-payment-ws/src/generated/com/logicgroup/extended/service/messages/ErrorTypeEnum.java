
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="CryptoLib"/>
 *     &lt;enumeration value="DefermentWebService"/>
 *     &lt;enumeration value="SolveSE"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorTypeEnum")
@XmlEnum
public enum ErrorTypeEnum {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("CryptoLib")
    CRYPTO_LIB("CryptoLib"),
    @XmlEnumValue("DefermentWebService")
    DEFERMENT_WEB_SERVICE("DefermentWebService"),
    @XmlEnumValue("SolveSE")
    SOLVE_SE("SolveSE"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    ErrorTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorTypeEnum fromValue(String v) {
        for (ErrorTypeEnum c: ErrorTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
