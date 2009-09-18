package com.virginmoneygiving.profanity.service;

import com.virginmoneygiving.profanity.model.ProfanityCheckResult;

/**
 * Service interface for profanity checking.
 *
 * @author Puneet Swarup
 */
public interface ProfaneService {

    /**
     * Check profanity for given input text.
     * <p>
     * The profanity check would cover unacceptable words as well as VMG
     * screened and non VMG screened words.
     *
     * @param inputText the input text to check.
     * @return result of type {@link ProfanityCheckResult}
     */
    ProfanityCheckResult checkProfanity(String inputText);

    /**
     * Check profanity for unacceptable words only.
     *
     * @param inputText the input text to check.
     * @return result of type {@link ProfanityCheckResult}
     */
    ProfanityCheckResult checkProfanityForUnacceptableWords(String inputText);

    /**
     * Check profanity for VMG screened words only.
     *
     * @param inputText the input text to check.
     * @return result of type {@link ProfanityCheckResult}
     */
    ProfanityCheckResult checkProfanityForVMGScreenedWords(String inputText);

    /**
     * Check profanity for non VMG screened words only.
     *
     * @param inputText the input text to check.
     * @return result of type {@link ProfanityCheckResult}
     */
    ProfanityCheckResult checkProfanityForNonVMGScreenedWords(String inputText);
}
