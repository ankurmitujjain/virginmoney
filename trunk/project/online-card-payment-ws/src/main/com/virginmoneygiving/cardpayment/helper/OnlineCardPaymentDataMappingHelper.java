package com.virginmoneygiving.cardpayment.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import com.virginmoneygiving.cardpayment.business.CardDetails;
import com.virginmoneygiving.cardpayment.business.PaymentDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServiceCardDetails;
import com.virginmoneygiving.cardpayment.service.messages.ServiceNameValuePair;
import com.virginmoneygiving.cardpayment.service.messages.ServicePaymentDetails;

/**
 * Data mappings.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class OnlineCardPaymentDataMappingHelper {

    /** The dozer. */
    @Resource
    private DozerBeanMapper dozer = null;

    /**
     * Sets the dozer.
     * 
     * @param dozer the dozer to set
     */
    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    /**
     * Map card details.
     * 
     * @param serviceCardDetails the service card details
     * 
     * @return the card details
     */
    public CardDetails mapCardDetails(ServiceCardDetails serviceCardDetails) {
        CardDetails cardDetails = new CardDetails();
        dozer.map(serviceCardDetails, cardDetails);

        return cardDetails;
    }

    /**
     * Map payment details.
     * 
     * @param servicePaymentDetails the service payment details
     * 
     * @return the payment details
     */
    public PaymentDetails mapPaymentDetails(ServicePaymentDetails servicePaymentDetails) {
        PaymentDetails paymentDetails = new PaymentDetails();
        dozer.map(servicePaymentDetails, paymentDetails);

        return paymentDetails;
    }

    /**
     * Map nvp list.
     * 
     * @param sourceList the source list
     * 
     * @return the map< string, string>
     */
    public static Map<String, String> mapNVPList(List<ServiceNameValuePair> sourceList) {
        Map<String, String> targetMap = new HashMap<String, String>();

        // iterative mapping
        for (ServiceNameValuePair nvp : sourceList) {
            targetMap.put(nvp.getName(), nvp.getValue());
        }

        return targetMap;
    }

    /**
     * Map to nvp list.
     * 
     * @param sourceMap the source map
     * 
     * @return the list< service name value pair>
     */
    public static List<ServiceNameValuePair> mapToNVPList(Map<String, String> sourceMap) {
        List<ServiceNameValuePair> targetList = new ArrayList<ServiceNameValuePair>();

        // iterative mapping
        Iterator<Entry<String,String>> it = sourceMap.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String,String> entry = it.next();
            ServiceNameValuePair nvp = new ServiceNameValuePair();
            nvp.setName(entry.getKey());
            nvp.setValue(entry.getValue());

            targetList.add(nvp);
        }

        return targetList;
    }
}
