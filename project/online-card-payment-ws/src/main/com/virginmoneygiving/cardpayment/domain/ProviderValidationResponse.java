package com.virginmoneygiving.cardpayment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Card validation response.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
@Entity
public class ProviderValidationResponse extends BaseProviderResponse {

    /** Is the card valid?. */
    private String validationPassed;

    /**
     * Gets the validation passed.
     * 
     * @return the validationPassed
     */
    @Column(name = "VALIDATION_PASSED", updatable = false, length = 3)
    public String getValidationPassed() {
        return validationPassed;
    }

    /**
     * Sets the validation passed.
     * 
     * @param validationPassed the validationPassed to set
     */
    public void setValidationPassed(String validationPassed) {
        this.validationPassed = validationPassed;
    }
}
