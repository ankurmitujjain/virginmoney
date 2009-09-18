
package com.virginmoneygiving.indexerservice.messages;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IndexedType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IndexedType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="FUNDRAISER_ACTIVITY"/>
 *     &lt;enumeration value="EVENT"/>
 *     &lt;enumeration value="CHARITY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IndexedType")
@XmlEnum
public enum IndexedType {

    FUNDRAISER_ACTIVITY,
    EVENT,
    CHARITY;

    public String value() {
        return name();
    }

    public static IndexedType fromValue(String v) {
        return valueOf(v);
    }

}
