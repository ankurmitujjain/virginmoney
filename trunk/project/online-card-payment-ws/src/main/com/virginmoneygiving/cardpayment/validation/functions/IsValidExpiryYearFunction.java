package com.virginmoneygiving.cardpayment.validation.functions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.lang.StringUtils;
import org.springmodules.validation.valang.functions.AbstractFunction;
import org.springmodules.validation.valang.functions.Function;

/**
 * Allow Valang Validator to check credit card expiry year is >= current year.
 * This implementation is not null safe.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class IsValidExpiryYearFunction extends AbstractFunction {

    /**
     * Required constructor.
     * 
     * @param arguments the arguments
     * @param line the line
     * @param column the column
     */
    public IsValidExpiryYearFunction(Function[] arguments, int line, int column) {
        super(arguments, line, column);
        definedExactNumberOfArguments(1);
    }

    /** (@inheritDoc) */
    @Override
    protected Object doGetResult(Object target) throws Exception {
        String str = getArguments()[0].getResult(target).toString();

        if (!StringUtils.isBlank(str) && StringUtils.isNumeric(str)) {
            // parse the arg
            int arg = Integer.parseInt(str);

            // get current year from calendar
            Calendar cal = new GregorianCalendar();
            int currYear = cal.get(Calendar.YEAR);

            // compare
            return arg >= currYear; 
        } else {
            // not numeric so return false
            return false;
        }
    }
}
