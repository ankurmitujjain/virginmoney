
package com.logicgroup.basic.service.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionSecurityEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionSecurityEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="WithSet"/>
 *     &lt;enumeration value="WithOutSet"/>
 *     &lt;enumeration value="ByMerchantCertificate"/>
 *     &lt;enumeration value="BySecureSession"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionSecurityEnum")
@XmlEnum
public enum TransactionSecurityEnum {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("WithSet")
    WITH_SET("WithSet"),
    @XmlEnumValue("WithOutSet")
    WITH_OUT_SET("WithOutSet"),
    @XmlEnumValue("ByMerchantCertificate")
    BY_MERCHANT_CERTIFICATE("ByMerchantCertificate"),
    @XmlEnumValue("BySecureSession")
    BY_SECURE_SESSION("BySecureSession");
    private final String value;

    TransactionSecurityEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransactionSecurityEnum fromValue(String v) {
        for (TransactionSecurityEnum c: TransactionSecurityEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
