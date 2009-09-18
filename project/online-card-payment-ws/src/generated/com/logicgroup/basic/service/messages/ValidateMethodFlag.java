
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidateMethodFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValidateMethodFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="SolveSE"/>
 *     &lt;enumeration value="Internal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValidateMethodFlag")
@XmlEnum
public enum ValidateMethodFlag {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("SolveSE")
    SOLVE_SE("SolveSE"),
    @XmlEnumValue("Internal")
    INTERNAL("Internal");
    private final String value;

    ValidateMethodFlag(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValidateMethodFlag fromValue(String v) {
        for (ValidateMethodFlag c: ValidateMethodFlag.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
