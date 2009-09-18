package com.virginmoneygiving.profanity.constants;

/**
 * Master data code constant references for Profanity Database.
 *
 * @author Puneet Swarup
 */
public final class MasterDataCodeConstants {

    /**
     * Private Constructor.
     */
    private MasterDataCodeConstants() {
        // Empty private constructor.
    }

    /**
     * Profane phrase category.
     */
    public enum ProfanePhraseCategory {

        /** Unacceptable profane phrase. */
        UNACCEPTABLE_PROFANE_PHRASE("UN_ACC"),

        /** VMG screened profane phrase. */
        VIRGIN_SCREENED_PROFANE_PHRASE("SCR_VIR"),

        /** Non virgin screened profane phrase. */
        NON_VIRGIN_SCREENED_PROFANE_PHRASE("SCR_NONVIR");

        /** Profane phrase category code. */
        private final String code;

        /**
         * The Constructor.
         *
         * @param categoryCode the categoryCode to set.
         */
        ProfanePhraseCategory(final String categoryCode) {
            this.code = categoryCode;
        }

        /**
         * Gets the code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }
    }
}
