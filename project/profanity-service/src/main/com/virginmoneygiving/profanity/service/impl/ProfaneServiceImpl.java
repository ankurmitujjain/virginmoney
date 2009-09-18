package com.virginmoneygiving.profanity.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virginmoneygiving.profanity.dao.ProfaneDAO;
import com.virginmoneygiving.profanity.model.ProfanityCheckResult;
import com.virginmoneygiving.profanity.model.ProfanityCloseChars;
import com.virginmoneygiving.profanity.model.ProfanityWords;
import com.virginmoneygiving.profanity.service.ProfaneService;
import com.virginmoneygiving.profanity.utils.ProfaneServiceHelper;

/**
 * Service implementation for {@link ProfaneService}.
 *
 * @author Puneet Swarup
 */
public class ProfaneServiceImpl implements ProfaneService {

    /** Serial version UID. */
    private static final long serialVersionUID = -124312467867L;

    /** Logger to log events. */
    private static final Logger LOGGER =
            Logger.getLogger(ProfaneServiceImpl.class);

    /** Profane dao reference. */
    private ProfaneDAO profaneDAO;

    /**
     * Sets the profane dao.
     *
     * @param profaneDAO the profaneDAO to set
     */
    public void setProfaneDAO(ProfaneDAO profaneDAO) {
        this.profaneDAO = profaneDAO;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ProfanityCheckResult checkProfanity(String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanity() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Text for profanity check:" + inputText);
        }
        final ProfanityWords profanityWords =
                ProfaneServiceHelper.getProfaneWords(profaneDAO);

        final ProfanityCheckResult resultForUnacceptableWords =
                ProfaneServiceHelper.checkProfanityForUnAccepatbleWords(
                        profanityWords.getUnAcceptablePhrases(), inputText);

        final ProfanityCheckResult resultForVMGScreenedWords =
                ProfaneServiceHelper.checkProfanityForScreenedWords(
                        profanityWords.getVirginScreenedPhrases(), inputText);

        final ProfanityCheckResult resultForNonVMGScreenedWords =
                ProfaneServiceHelper
                        .checkProfanityForScreenedWords(profanityWords
                                .getNonVirginScreenedPhrases(), inputText);

        final ProfanityCheckResult result = new ProfanityCheckResult();
        result.setInputText(inputText);
        result.setStatus(resultForUnacceptableWords.isStatus());
        result.setOutputText(resultForUnacceptableWords.getOutputText());

        if (resultForVMGScreenedWords.getScreenedVirginWordsResult() != null) {
            result.setScreenedVirginWordsResult(resultForVMGScreenedWords
                    .getScreenedVirginWordsResult());
        }

        if (resultForNonVMGScreenedWords.getScreenedVirginWordsResult() != null) {
            result.setScreenedNonVirginWordsResult(resultForNonVMGScreenedWords.
                    getScreenedVirginWordsResult());
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanity() method - END");
        }
        return result;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ProfanityCheckResult checkProfanityForUnacceptableWords(
            String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForUnacceptableWords() method - START");
        }

        final ProfanityWords profanityWords =
            ProfaneServiceHelper.getProfaneWords(profaneDAO);

        final ProfanityCheckResult resultForUnacceptableWords =
            ProfaneServiceHelper.checkProfanityForUnAccepatbleWords(
                    profanityWords.getUnAcceptablePhrases(), inputText);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForUnacceptableWords() method - END");
        }
        return resultForUnacceptableWords;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ProfanityCheckResult checkProfanityForVMGScreenedWords(
            String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForVMGScreenedWords() method - START");
        }

        final ProfanityWords profanityWords =
            ProfaneServiceHelper.getProfaneWords(profaneDAO);

        final ProfanityCheckResult resultForVMGScreenedWords =
            ProfaneServiceHelper.checkProfanityForScreenedWords(
                    profanityWords.getVirginScreenedPhrases(), inputText);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForVMGScreenedWords() method - END");
        }
        return resultForVMGScreenedWords;
    }

    /** {@inheritDoc} */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ProfanityCheckResult checkProfanityForNonVMGScreenedWords(
            String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForNonVMGScreenedWords() method - START");
        }

        final ProfanityWords profanityWords =
            ProfaneServiceHelper.getProfaneWords(profaneDAO);

        final ProfanityCheckResult resultForNonVMGScreenedWords =
            ProfaneServiceHelper.checkProfanityForScreenedWords(
                    profanityWords.getNonVirginScreenedPhrases(), inputText);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceImpl::checkProfanityForNonVMGScreenedWords() method - END");
        }
        return resultForNonVMGScreenedWords;
    }
}
