package com.virginmoneygiving.profanity.dao;

import java.util.List;

import com.virginmoneygiving.profanity.domain.ProfanePhrase;

/**
 * DAO interface to access Profane schema.
 *
 * @author Puneet Swarup
 */
public interface ProfaneDAO {

    /**
     * Fetch all the profane phrases from database.
     *
     * @return list of {@link ProfanePhrase}
     */
    List<ProfanePhrase> fetchAllProfanePhrases();
}
