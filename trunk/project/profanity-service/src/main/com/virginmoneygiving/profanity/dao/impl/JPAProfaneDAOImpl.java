package com.virginmoneygiving.profanity.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springmodules.cache.annotations.Cacheable;

import com.virginmoneygiving.profanity.dao.ProfaneDAO;
import com.virginmoneygiving.profanity.domain.ProfanePhrase;

/**
 * DAO implementation for {@link ProfaneDAO}.
 *
 * @author Puneet Swarup - Modified implementation.
 */

public class JPAProfaneDAOImpl implements ProfaneDAO {

    /** Logger for logging. */
    private static Logger LOGGER = Logger.getLogger(JPAProfaneDAOImpl.class);

    /** An Entity Manager Instance. */
    private EntityManager entityManager;

    /**
     * Dependency injection of JPA EntityManager.
     *
     * @param entityManager an entity manager.
     */
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Cacheable(modelId = "staticDataCaching")
    public List<ProfanePhrase> fetchAllProfanePhrases() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAProfaneDAOImpl::fetchAllProfanePhrases() method - START");
        }
        List<ProfanePhrase> profanePhraseList = entityManager.createNamedQuery("fetchAllProfanePhrases")
                    .getResultList();
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("JPAProfaneDAOImpl::fetchAllProfanePhrases() method - END");
        }
        return profanePhraseList;
    }
}
