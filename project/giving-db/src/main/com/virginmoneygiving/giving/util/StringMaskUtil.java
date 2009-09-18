package com.virginmoneygiving.giving.util;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

/**
 * Provides utility functions for masking strings with a mask character. This
 * can be used, for example, for masking out part of a password, credit card
 * number, etc., for display purposes.
 * <p/>
 * 
 * @author Diptirmaya Rout
 */
public final class StringMaskUtil {

    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(StringMaskUtil.class);
	
	/** The default mask character. */
    public static final char MASK_CHARACTER = '*';

    /** Error messages. */
    private static final String ERRMSG_UNMASKED_COUNT_TOO_LARGE =
            "A string of length {0} cannot be masked leaving {1} trailing characters as plain text";

    /**
     * Masks a section of a string leaving a specified number of unmasked trailing suffix
     * characters and masking a specified number of preceding characters. Any additional
     * leading characters will also be unmasked.
     * 
     * @param plainText the string to be masked.
     * @param unmaskedSuffixCharCount the number of trailing unmasked characters that the string
     * should contain.
     * @param maskedCharCount the desired number of characters to be masked out.
     * @param maskChar the character that will be used in place of the original character.
     * 
     * @return the masked string, or null> if the input string is null.
     * the length of the string.
     */
    public static String maskFromEnd(String plainText,
            int unmaskedSuffixCharCount, int maskedCharCount, char maskChar) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("StringMaskUtil::maskFromEnd() method - START");
        }
        final String result;
        if (plainText == null) {
            result = null;
        }
        else if (unmaskedSuffixCharCount >= plainText.length()) {
            LOGGER.error("Error Occured Inside maskFromEnd() method method ");
            LOGGER.error("INPUT : plainText =" + plainText );
            throw new IllegalArgumentException(MessageFormat.format(
                    ERRMSG_UNMASKED_COUNT_TOO_LARGE, new Object[] {
                            new Integer(plainText.length()),
                            new Integer(unmaskedSuffixCharCount) }));
        }
        else {
            StringBuffer buf = new StringBuffer(plainText);

            if (unmaskedSuffixCharCount < 0) {
                unmaskedSuffixCharCount = 0;
            }

            if (maskedCharCount <= 0) {
                maskedCharCount = plainText.length();
            }

            int nextMaskedIndex =
                    plainText.length() - unmaskedSuffixCharCount - 1;
            for (int i = 0; i < maskedCharCount && nextMaskedIndex >= 0; i++, nextMaskedIndex--) {
                buf.setCharAt(nextMaskedIndex, maskChar);
            }

            result = buf.toString();
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("StringMaskUtil::maskFromEnd() method - END");
        }
        return result;
    }

    /**
     * Private constructor to ensure no external instances are created. This
     * class should be used via the static methods.
     */
    private StringMaskUtil() {
    }
}
