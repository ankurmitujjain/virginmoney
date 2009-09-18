package com.virginmoneygiving.indexerservice.client.event;

/**
 * Enum possible indexer actions.
 *
 * @author ian.priest@opsera.com
 */
public enum IndexerEventType {

    DELETE,
    UPDATE;

    public String value() {
        return name();
    }

    public static IndexerEventType fromValue(String v) {
        return valueOf(v);
    }
    
}
