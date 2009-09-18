package com.virginmoneygiving.cardpayment.validation.functions;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springmodules.validation.valang.functions.AbstractFunction;
import org.springmodules.validation.valang.functions.Function;
import com.virginmoneygiving.cardpayment.helper.LogicGroupConstants;
import com.virginmoneygiving.cardpayment.helper.OnlineCardPaymentDataMappingHelper;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;

/**
 * Validator for Authentication Response NVP List.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class CheckAuthenticationResponseFunction extends AbstractFunction {

    /** Logger. */
    private static final Logger LOGGER = Logger
            .getLogger(CheckAuthenticationResponseFunction.class);

    /**
     * Required constructor.
     * 
     * @param arguments the arguments
     * @param line the line
     * @param column the column
     */
    public CheckAuthenticationResponseFunction(Function[] arguments, int line,
            int column) {
        super(arguments, line, column);
        definedExactNumberOfArguments(1);
    }

    /** (@inheritDoc) */
    @Override
    protected Object doGetResult(Object target) throws Exception {
        // As this is an overridden method therefore we are forced to throw
        // Exception
        Object obj = getArguments()[0].getResult(target);

        if (obj instanceof List) {
            try {
                List<ServiceNameValuePair> list = (List<ServiceNameValuePair>) obj;
                Object returnObject = validateAuthResponseData(list);

                return returnObject;
            } catch (ClassCastException cce) {
                LOGGER.error("doGetResult() - Object target=" + target
                        + ", Object obj=" + obj + ", ClassCastException cce="
                        + cce, cce);
                LOGGER.debug("Given wrong type of data to process");
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Check that data has enough elements and contains specific data items.
     * 
     * @param authResponseData from 3D Secure
     * 
     * @return true iff the list is valid
     */
    private boolean validateAuthResponseData(
            List<ServiceNameValuePair> authResponseData) {

        // min 15 entries
        if (authResponseData.size() < 15) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Auth response data has too few entries: ");
            }
        }

        // convert to a map for ease of use
        Map<String, String> map = OnlineCardPaymentDataMappingHelper
                .mapNVPList(authResponseData);

        boolean returnboolean = checkMap(map, LogicGroupConstants.DIGEST_KEY)
                && checkMap(map, LogicGroupConstants.HTTP_VERSION_KEY)
                && checkMap(map, LogicGroupConstants.MERCHANT_ID_KEY)
                && checkMap(map, LogicGroupConstants.XID_KEY)
                && checkMap(map, LogicGroupConstants.RES_MD_STATUS_KEY)
                && checkMap(map, LogicGroupConstants.RES_MD_ERROR_MSG_KEY)
                && checkMap(map, LogicGroupConstants.RES_TX_STATUS_KEY)
                && checkMap(map, LogicGroupConstants.RES_IREQ_CODE_KEY)
                && checkMap(map, LogicGroupConstants.RES_IREQ_DETAIL_KEY)
                && checkMap(map, LogicGroupConstants.RES_VENDOR_CODE_KEY)
                && checkMap(map, LogicGroupConstants.RES_ECI_KEY)
                && checkMap(map, LogicGroupConstants.RES_CAVV_KEY)
                && checkMap(map, LogicGroupConstants.RES_CAVV_ALG_KEY)
                && checkMap(map, LogicGroupConstants.MERCHANT_DATA_KEY)
                && checkMap(map, LogicGroupConstants.RES_OPAL_ERROR_CODE_KEY);

        return returnboolean;
    }

    /**
     * Check map contains the key.
     * 
     * @param map the map
     * @param key the key
     * 
     * @return true, if check map
     */
    private boolean checkMap(Map<String, String> map, String key) {
        if (!map.containsKey(key)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Authentication response missing " + key);
            }
            return false;
        }
        return true;
    }
}
