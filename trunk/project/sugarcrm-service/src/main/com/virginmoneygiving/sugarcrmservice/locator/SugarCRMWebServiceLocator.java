package com.virginmoneygiving.sugarcrmservice.locator;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.virginmoneygiving.sugarcrmservice.service.messages.Sugarsoap;
import com.virginmoneygiving.sugarcrmservice.service.messages.SugarsoapLocator;
import com.virginmoneygiving.sugarcrmservice.service.messages.SugarsoapPortType;

import javax.xml.rpc.ServiceException;

/**
 * Factory for building an instance of the SugarCRM web service.
 * 
 * @author Vikas Kale
 */
public class SugarCRMWebServiceLocator {

	/**
	 *creating Logger Instance.
	 */
	private static Logger LOGGER = Logger
			.getLogger(SugarCRMWebServiceLocator.class);

	/**
	 * OnlineCardPaymentWs Instance.
	 */
	private static SugarsoapPortType sugarsoapPortType = null;

	/**
	 * To hold the web service URL of SugarCRM.
	 */
	private String webServiceURL;

	/**
	 * Return the instance of {@link SugarsoapPortType} for configured web
	 * service Url.
	 * 
	 * @return port object of {@link SugarsoapPortType}.
	 * @exception throws {@link MalformedURLException}.
	 * @exception throws {@link ServiceException}.
	 */
	public SugarsoapPortType getSugarsoapPortType() {
		Sugarsoap service = new SugarsoapLocator();
		SugarsoapPortType port = null;
		try {
			port = service
					.getsugarsoapPort(new java.net.URL(getWebServiceURL()));
		} catch (MalformedURLException malformedUrlException) {
			LOGGER
					.error("Thrown MalformedURLException in SugarCRMWebServiceLocator.getsugarsoapPort for URL::"
							+ getWebServiceURL());
			LOGGER.error(
					"Thrown MalformedURLException in SugarCRMWebServiceLocator.getsugarsoapPort"
							+ malformedUrlException.getMessage(),
					malformedUrlException);
		} catch (ServiceException serviceException) {
			LOGGER
					.error("Thrown ServiceException in SugarCRMWebServiceLocator.getsugarsoapPort for URL::"
							+ getWebServiceURL());
			LOGGER.error(
					"Thrown ClassNotFoundException in SugarCRMWebServiceLocator.getsugarsoapPort"
							+ serviceException.getMessage(), serviceException);
		}

		return port;
	}

	/**
	 * @return
	 */
	public String getWebServiceURL() {
		return webServiceURL;
	}

	/**
	 * @param webServiceURL
	 */
	public void setWebServiceURL(String webServiceURL) {
		this.webServiceURL = webServiceURL;
	}

}
