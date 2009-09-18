package com.virginmoneygiving.cardpayment.business;

/**
 * Covers processing steps for Authentication operations.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public enum AuthenticateActionEnum {

    /** Authorise using 3D. */
    AUTH_3D,

    /** Authorise as non-3D. */
    AUTH_NON_3D,

    /** An error state. */
    ERROR;
}
