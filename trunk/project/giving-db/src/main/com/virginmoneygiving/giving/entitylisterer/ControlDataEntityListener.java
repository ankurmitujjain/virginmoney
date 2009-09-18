package com.virginmoneygiving.giving.entitylisterer;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import com.virginmoneygiving.giving.domain.AuditInterface;

/**
 * Ensure control data is set on persisted/updated entities.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class ControlDataEntityListener {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(ControlDataEntityListener.class);

    /**
     * Method being called before persisting any entity.
     * 
     * @param entity the entity being persisted.
     */
    @PrePersist
    void prePersist(AuditInterface entity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("In prePersist method...");
        }

        // get data from ThreadLocal
        ControlData controlData = ThreadLocalControlDataStorage.get();

        // if present
        if (controlData != null) {
            LOGGER.debug("Mapping created columns...");
            // map to update columns
            entity.setCreatedIPAddress(controlData.getIpAddress());
            entity.setCreatedProcess(controlData.getProcess());
            entity.setCreatedUser(controlData.getUsername());

            // job done!
        } else {
            LOGGER.info("No control data...");
        }
    }

    /**
     * Method being called before updating any entity.
     * 
     * @param entity the entity being updated.
     */
    @PreUpdate
    void preUpdate(AuditInterface entity) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("In prePersist method...");
        }

        // get data from ThreadLocal
        ControlData cData = ThreadLocalControlDataStorage.get();

        // if present
        if (cData != null) {
            LOGGER.debug("Mapping updated columns...");
            // map to update columns
            entity.setUpdatedIPAddress(cData.getIpAddress());
            entity.setUpdatedProcess(cData.getProcess());
            entity.setUpdatedUser(cData.getUsername());

            // job done!
        } else {
            LOGGER.info("No control data...");
        }
    }
}
