package com.virginmoneygiving.cardpayment.validation.functions;

import org.apache.commons.lang.StringUtils;
import org.springmodules.validation.valang.functions.AbstractFunction;
import org.springmodules.validation.valang.functions.Function;

/**
 * Allow Valang Validator to use Commons Lang StringUtils.isNumeric() and
 * !StringUtils.isEmpty() to enforce length. This implementation is not null
 * safe.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class IsNumericFunction extends AbstractFunction {

    /**
     * Required constructor.
     * 
     * @param arguments the arguments
     * @param line the line
     * @param column the column
     */
    public IsNumericFunction(Function[] arguments, int line, int column) {
        super(arguments, line, column);
        definedExactNumberOfArguments(1);
    }

    /** (@inheritDoc) */
    @Override
    protected Object doGetResult(Object target) throws Exception {
        String str = getArguments()[0].getResult(target).toString();

        Object returnObject = StringUtils.isNumeric(str)
                && !StringUtils.isEmpty(str);

        return returnObject;
    }
}
