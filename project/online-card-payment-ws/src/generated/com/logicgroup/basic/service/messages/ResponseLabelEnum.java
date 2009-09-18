
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseLabelEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResponseLabelEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Validated"/>
 *     &lt;enumeration value="Terminal"/>
 *     &lt;enumeration value="Online"/>
 *     &lt;enumeration value="Manual"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="Cancelled"/>
 *     &lt;enumeration value="UnableToCancel"/>
 *     &lt;enumeration value="GetManualAuthorisation"/>
 *     &lt;enumeration value="GetSignatureAuthorisation"/>
 *     &lt;enumeration value="Telephone"/>
 *     &lt;enumeration value="GetManualAuthorisationAndResubmit"/>
 *     &lt;enumeration value="HotCardDeclined"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResponseLabelEnum")
@XmlEnum
public enum ResponseLabelEnum {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Validated")
    VALIDATED("Validated"),
    @XmlEnumValue("Terminal")
    TERMINAL("Terminal"),
    @XmlEnumValue("Online")
    ONLINE("Online"),
    @XmlEnumValue("Manual")
    MANUAL("Manual"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled"),
    @XmlEnumValue("UnableToCancel")
    UNABLE_TO_CANCEL("UnableToCancel"),
    @XmlEnumValue("GetManualAuthorisation")
    GET_MANUAL_AUTHORISATION("GetManualAuthorisation"),
    @XmlEnumValue("GetSignatureAuthorisation")
    GET_SIGNATURE_AUTHORISATION("GetSignatureAuthorisation"),
    @XmlEnumValue("Telephone")
    TELEPHONE("Telephone"),
    @XmlEnumValue("GetManualAuthorisationAndResubmit")
    GET_MANUAL_AUTHORISATION_AND_RESUBMIT("GetManualAuthorisationAndResubmit"),
    @XmlEnumValue("HotCardDeclined")
    HOT_CARD_DECLINED("HotCardDeclined");
    private final String value;

    ResponseLabelEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseLabelEnum fromValue(String v) {
        for (ResponseLabelEnum c: ResponseLabelEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
