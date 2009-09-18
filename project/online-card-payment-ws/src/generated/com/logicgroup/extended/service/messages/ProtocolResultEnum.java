
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProtocolResultEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProtocolResultEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Successful"/>
 *     &lt;enumeration value="Attempted"/>
 *     &lt;enumeration value="UnableToVerify"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProtocolResultEnum")
@XmlEnum
public enum ProtocolResultEnum {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful"),
    @XmlEnumValue("Attempted")
    ATTEMPTED("Attempted"),
    @XmlEnumValue("UnableToVerify")
    UNABLE_TO_VERIFY("UnableToVerify");
    private final String value;

    ProtocolResultEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProtocolResultEnum fromValue(String v) {
        for (ProtocolResultEnum c: ProtocolResultEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
