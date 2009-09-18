package com.virginmoneygiving.profanity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.virginmoneygiving.profanity.constants.MasterDataCodeConstants;
import com.virginmoneygiving.profanity.dao.ProfaneDAO;
import com.virginmoneygiving.profanity.domain.ProfanePhrase;
import com.virginmoneygiving.profanity.model.ProfanityCheckResult;
import com.virginmoneygiving.profanity.model.ProfanityCloseChars;
import com.virginmoneygiving.profanity.model.ProfanityWords;

/**
 * Utility class to provide helper methods for {@link ProfaneService}.
 *
 * @author Puneet Swarup
 */
public final class ProfaneServiceHelper {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(ProfaneServiceHelper.class);

    /** Replacement for profane phrase. */
    private static final String PROFANE_PHRASE_REPLACEMENT = "****";

    /** Number of characters before and after the profane word. */
    private static final Integer PRE_POST_TEXT_LENGTH = 50;

    /**
     * Private Constructor.
     */
    private ProfaneServiceHelper() {
        // Empty constructor.
    }

    /**
     * Fetch all the profane phrases from the database and segregate them
     * according to the profane phrase type.
     *
     * @param profaneDAO the {@link ProfaneDAO}
     * @return the segregated profane phrases in {@link ProfanityWords}
     */
    public static ProfanityWords getProfaneWords(final ProfaneDAO profaneDAO) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::getProfaneWords() method - START");
        }

        final List<ProfanePhrase> profanePhrases = profaneDAO.fetchAllProfanePhrases();
        ProfanityWords profanityWords = null;

        if (profanePhrases != null && profanePhrases.size() != 0) {
            profanityWords = new ProfanityWords();
            for (ProfanePhrase phrase : profanePhrases) {
                final String phraseType =
                        phrase.getProfanePhraseType().getCode();
                if (MasterDataCodeConstants.ProfanePhraseCategory.UNACCEPTABLE_PROFANE_PHRASE
                        .getCode().equals(phraseType)) {
                    profanityWords.getUnAcceptablePhrases().add(phrase);
                }
                else if (MasterDataCodeConstants.ProfanePhraseCategory.VIRGIN_SCREENED_PROFANE_PHRASE
                        .getCode().equals(phraseType)) {
                    profanityWords.getVirginScreenedPhrases().add(phrase);
                }
                else if (MasterDataCodeConstants.ProfanePhraseCategory.NON_VIRGIN_SCREENED_PROFANE_PHRASE
                        .getCode().equals(phraseType)) {
                    profanityWords.getNonVirginScreenedPhrases().add(phrase);
                }
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::getProfaneWords() method - END");
        }
        return profanityWords;
    }

    /**
     * Test the given text for profanity of unacceptable words.
     *
     * @param profaneDAO the {@link ProfaneDAO}
     * @param inputText the input to be tested for profanity.
     * @return result as {@link ProfanityCheckResult}
     */
    public static ProfanityCheckResult checkProfanityForUnAccepatbleWords(
            final ProfaneDAO profaneDAO, final String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForUnAccepatbleWords() method - START");
        }

        ProfanityCheckResult result = new ProfanityCheckResult();
        result.setStatus(true);
        result.setInputText(inputText);
        result.setOutputText(inputText);

        if (inputText != null) {
            result =
                    checkProfanityForUnAccepatbleWords(getProfaneWords(
                            profaneDAO).getUnAcceptablePhrases(), inputText);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForUnAccepatbleWords() method - END");
        }
        return result;
    }

    /**
     * Test the given text for profanity of VMG screened words.
     *
     * @param profaneDAO the {@link ProfaneDAO}
     * @param inputText the input to be tested for profanity.
     * @return result as {@link ProfanityCheckResult}
     */
    public static ProfanityCheckResult checkProfanityForVMGScreenedWords(
            final ProfaneDAO profaneDAO, final String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForVMGScreenedWords() method - START");
        }

        ProfanityCheckResult result = new ProfanityCheckResult();
        result.setStatus(true);
        result.setInputText(inputText);
        result.setOutputText(inputText);

        if (inputText != null) {
            result =
                checkProfanityForScreenedWords(getProfaneWords(
                            profaneDAO).getVirginScreenedPhrases(), inputText);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForVMGScreenedWords() method - END");
        }
        return result;
    }

    /**
     * Test the given text for profanity of non-VMG screened words.
     *
     * @param profaneDAO the {@link ProfaneDAO}
     * @param inputText the input to be tested for profanity.
     * @return result as {@link ProfanityCheckResult}
     */
    public static ProfanityCheckResult checkProfanityForNonVMGScreenedWords(
            final ProfaneDAO profaneDAO, final String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForNonVMGScreenedWords() method - START");
        }

        ProfanityCheckResult result = new ProfanityCheckResult();
        result.setStatus(true);
        result.setInputText(inputText);
        result.setOutputText(inputText);

        if (inputText != null) {
            result =
                checkProfanityForScreenedWords(getProfaneWords(
                            profaneDAO).getNonVirginScreenedPhrases(), inputText);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForNonVMGScreenedWords() method - END");
        }
        return result;
    }

    /**
     * Test the given text for profanity of unacceptable words.
     *
     * @param unAcceptablePhrases the un-acceptable profane phrases.
     * @param inputText the input text to test.
     * @return result as {@link ProfanityCheckResult}
     */
    public static ProfanityCheckResult checkProfanityForUnAccepatbleWords(
            final List<ProfanePhrase> unAcceptablePhrases,
            final String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForUnAccepatbleWords() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Unacceptable word list size:" + unAcceptablePhrases.size());
        }
        ProfanityCheckResult result = new ProfanityCheckResult();
        result.setStatus(true);
        result.setInputText(inputText);
        result.setOutputText(inputText);

        if (inputText != null && unAcceptablePhrases != null
                && unAcceptablePhrases.size() != 0) {
            for (ProfanePhrase phrase : unAcceptablePhrases) {
                final String phraseText = phrase.getProfanePhrase();
                final StringBuffer resultString = new StringBuffer();
                final Pattern pattern =
                        Pattern.compile(phraseText, Pattern.CASE_INSENSITIVE);
                final Matcher matcher = pattern.matcher(result.getOutputText());

                while (matcher.find()) {
                    if (isWordSuitableForReplacement(result.getOutputText(), phraseText,
                            matcher.start())) {
                        matcher.appendReplacement(resultString,
                                PROFANE_PHRASE_REPLACEMENT);
                        result.setStatus(false);
                    }
                }
                matcher.appendTail(resultString);
                result.setOutputText(resultString.toString());
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForUnAccepatbleWords() method - END");
        }
        return result;
    }

    /**
     * Test the given text for profanity of screened words.
     *
     * @param profanePhrases the un-acceptable profane phrases.
     * @param inputText the input text to test.
     * @return result as {@link ProfanityCheckResult}
     */
    public static ProfanityCheckResult checkProfanityForScreenedWords(
            final List<ProfanePhrase> profanePhrases, final String inputText) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForScreenedWords() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Screened word list size:" + profanePhrases.size());
        }

        final ProfanityCheckResult result = new ProfanityCheckResult();

        if (inputText != null) {
            result.setStatus(true);
            result.setInputText(inputText);
            result.setOutputText(inputText);
            result.setScreenedVirginWordsResult(new ArrayList<ProfanityCloseChars>());

            for (ProfanePhrase phrase : profanePhrases) {
                final String phraseText = phrase.getProfanePhrase();
                final Pattern pattern =
                        Pattern.compile(phraseText, Pattern.CASE_INSENSITIVE);
                final Matcher matcher = pattern.matcher(inputText);

                while (matcher.find()) {
                    if (isWordSuitableForReplacement(inputText, phraseText,
                            matcher.start())) {
                        ProfanityCloseChars profanityCloseChars =
                                new ProfanityCloseChars();
                        final int prevIndex = matcher.start() - 1;
                        final int nextIndex =
                                prevIndex + phraseText.length() + 1;
                        profanityCloseChars.setScreenedWord(phraseText);
                        profanityCloseChars.setId(phrase.getId());
                        if (prevIndex > 0) {
                            if (prevIndex - PRE_POST_TEXT_LENGTH < 0) {
                                profanityCloseChars.setBeforeChars(inputText
                                        .substring(0, prevIndex));
                            }
                            else {
                                profanityCloseChars.setBeforeChars(inputText
                                        .substring(prevIndex
                                                - PRE_POST_TEXT_LENGTH,
                                                prevIndex));
                            }
                        }
                        else {
                            profanityCloseChars.setBeforeChars("");
                        }
                        if (nextIndex < inputText.length()) {
                            if (nextIndex + PRE_POST_TEXT_LENGTH >= inputText
                                    .length()) {
                                profanityCloseChars.setAfterChars(inputText
                                        .substring(nextIndex));
                            }
                            else {
                                profanityCloseChars.setAfterChars(inputText
                                        .substring(nextIndex, nextIndex
                                                + PRE_POST_TEXT_LENGTH));
                            }
                        }
                        else {
                            profanityCloseChars.setAfterChars("");
                        }
                        result.getScreenedVirginWordsResult()
                                .add(profanityCloseChars);
                    }
                    result.setStatus(false);
                }
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::checkProfanityForScreenedWords() method - END");
        }
        return result;
    }

    /**
     * Test whether the given word is eligible for replacement.
     * <p>
     * Some words may be matching the profane phrases, but may mean something
     * else if pre-fixed or post-fixed with some characters. Here the word is
     * checked for the same.
     *
     * @param text the input text.
     * @param wordForCheck the word to check.
     * @param index the index of word in the input text.
     * @return true, if this word is eligible for replacement, else false.
     */
    private static boolean isWordSuitableForReplacement(final String text,
            final String wordForCheck, final int index) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::isWordSuitableForReplacement() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("User entred text = " + text);
            LOGGER.debug("Profane word found in the text = " + wordForCheck);
            LOGGER.debug("Index of profane word in the text = " + index);
        }
        int prevIndex = index - 1;
        int nextIndex = prevIndex + wordForCheck.length() + 1;

        if (StringUtils.equalsIgnoreCase(wordForCheck, text)) {
            return true;
        }

        if (prevIndex == -1 && nextIndex <= text.length()) {
            if (isCharPresentAtIndex(text, nextIndex)) {
                return true;
            }
        }
        if (prevIndex >= 0 && nextIndex >= text.length()) {
            if (isCharPresentAtIndex(text, prevIndex)) {
                return true;
            }
        }
        if(prevIndex < 0){
            prevIndex = 0;
        }
        if(nextIndex > text.length()){
            nextIndex = text.length();
        }
        if (isCharPresentAtIndex(text, prevIndex)
                && isCharPresentAtIndex(text, nextIndex)) {
            return true;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::isWordSuitableForReplacement() method - END");
        }
        return false;
    }

    /**
     * Test whether any special character is present at given index value.
     *
     * @param text the text to test
     * @param index the index
     * @return true, if special character present at given index, alse false.
     */
    private static boolean isCharPresentAtIndex(String text, int index) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::isCharPresentAtIndex() method - START");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("User entered text = " + text);
            LOGGER.debug("Previous or next index of profane word found in text =" + index);
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("ProfaneServiceHelper::isCharPresentAtIndex() method - END");
        }
        String preSufChars = "0123456789?!':;*().,£$%^&-_+={}[]@#~|<>/ \"\t\n\r\\`¬";
        String temp = String.valueOf(text.charAt(index));
        
        if (preSufChars.indexOf(temp) != -1) {
            return true;
        }
        return false;
    }
}
