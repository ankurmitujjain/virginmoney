package com.virginmoneygiving.cardpayment.validation.functions;

import java.math.BigDecimal;
import org.springmodules.validation.valang.functions.AbstractFunction;
import org.springmodules.validation.valang.functions.Function;

/**
 * Allow Valang Validator to check BigDecimal scale. This implementation is not
 * null safe.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class HasScaleFunction extends AbstractFunction {

    /**
     * Required constructor.
     * 
     * @param arguments the arguments
     * @param line the line
     * @param column the column
     */
    public HasScaleFunction(Function[] arguments, int line, int column) {
        super(arguments, line, column);
        definedExactNumberOfArguments(2);
    }

    /** (@inheritDoc) */
    @Override
    protected Object doGetResult(Object target) throws Exception {
        // exception from super class
        BigDecimal bd = new BigDecimal(getArguments()[0].getResult(target)
                .toString());
        Integer scale = new Integer(getArguments()[1].getResult(target)
                .toString());

        Object returnObject = (bd.scale() == scale.intValue());
        return returnObject;
    }
}
