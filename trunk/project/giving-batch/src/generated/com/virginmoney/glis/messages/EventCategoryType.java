
package com.virginmoney.glis.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PROCESS"/>
 *     &lt;enumeration value="REPROCESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventCategoryType")
@XmlEnum
public enum EventCategoryType {

    PROCESS,
    REPROCESS;

    public String value() {
        return name();
    }

    public static EventCategoryType fromValue(String v) {
        return valueOf(v);
    }

}
