package com.virginmoney.addresslookup.util;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import org.apache.log4j.Logger;

/**
 * Utility class to calculate elapsed times
 * @author woodsn
 *         <p/>
 *         Copyright     :  Virgin Money Ltd.
 */
public class TimingUtil {

    private static Logger logger = Logger.getLogger(TimingUtil.class);

    private static NumberFormat secondsFormatter = new DecimalFormat("###0.000");

    /**
     * Returns a string representation of the difference in seconds between two times.
      * @param startTime
     * @param finishTime
     * @return
     */
    public static String formatElapsedSeconds(long startTime, long finishTime) {
           return secondsFormatter.format((float)(finishTime - startTime) / 1000);
    }

}

