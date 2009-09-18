package com.virginmoneygiving.integrationtests.webdriver.operations;

/**
 * Java object representing Operations user login
 * @author saurabhh
 *
 */
public class OperationsUserDetails {

	/** Username of Operation user */
	private String userName;

	/** Password for Operations user */
	private String password;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
