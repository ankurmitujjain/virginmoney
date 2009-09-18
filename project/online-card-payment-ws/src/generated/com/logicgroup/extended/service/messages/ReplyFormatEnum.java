
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReplyFormatEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReplyFormatEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Standard"/>
 *     &lt;enumeration value="Extended"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReplyFormatEnum")
@XmlEnum
public enum ReplyFormatEnum {

    @XmlEnumValue("Standard")
    STANDARD("Standard"),
    @XmlEnumValue("Extended")
    EXTENDED("Extended");
    private final String value;

    ReplyFormatEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReplyFormatEnum fromValue(String v) {
        for (ReplyFormatEnum c: ReplyFormatEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
