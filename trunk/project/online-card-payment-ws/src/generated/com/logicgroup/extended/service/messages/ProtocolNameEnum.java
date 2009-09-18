
package com.logicgroup.extended.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProtocolNameEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProtocolNameEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="UCaf"/>
 *     &lt;enumeration value="Set"/>
 *     &lt;enumeration value="ThreeDSecure"/>
 *     &lt;enumeration value="Ssl"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProtocolNameEnum")
@XmlEnum
public enum ProtocolNameEnum {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("UCaf")
    U_CAF("UCaf"),
    @XmlEnumValue("Set")
    SET("Set"),
    @XmlEnumValue("ThreeDSecure")
    THREE_D_SECURE("ThreeDSecure"),
    @XmlEnumValue("Ssl")
    SSL("Ssl");
    private final String value;

    ProtocolNameEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProtocolNameEnum fromValue(String v) {
        for (ProtocolNameEnum c: ProtocolNameEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
