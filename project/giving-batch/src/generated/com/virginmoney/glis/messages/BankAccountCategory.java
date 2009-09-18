
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BankAccountCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BankAccountCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DONATION"/>
 *     &lt;enumeration value="TRADING"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BankAccountCategory")
@XmlEnum
public enum BankAccountCategory {

    DONATION,
    TRADING,
    OTHER;

    public String value() {
        return name();
    }

    public static BankAccountCategory fromValue(String v) {
        return valueOf(v);
    }

}
