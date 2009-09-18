
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationPassedEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValidationPassedEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="No"/>
 *     &lt;enumeration value="Yes"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ValidationPassedEnum")
@XmlEnum
public enum ValidationPassedEnum {

    @XmlEnumValue("No")
    NO("No"),
    @XmlEnumValue("Yes")
    YES("Yes");
    private final String value;

    ValidationPassedEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValidationPassedEnum fromValue(String v) {
        for (ValidationPassedEnum c: ValidationPassedEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
