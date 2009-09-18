package com.virginmoneygiving.giving.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.virginmoneygiving.giving.util.Constants;
import com.virginmoneygiving.giving.util.StringMaskUtil;

/**
 * DTO representing a payment card.
 * 
 * @author Siva Kumar
 * @author Puneet Swarup - added audit attributes and toString.
 */
@Entity
@Table(name = "PAYMENT_CARD")
@Audited
public class PaymentCard extends EnversAuditAttributes implements Serializable {
    
    /** Serial Version UID. */
    private static final long serialVersionUID = 2705833300964726522L;
    
    /** primarykey for payment card. */
    private Long id;
    
    /** Person id for relation with person. */
    private Person person;
    
    /** card provided code for relation with card_provider. */
    private CardProvider cardProvider;
    
    /** Attribute Bin Number. */
    private String binNumber;
    
    /** Attribute MaskedCardNumber. */
    private String maskedcardNumber;
    
    /** Attribute card name. */
    private String cardName;
    
    /** Attribute expiry month. */
    private Integer expiryMonth;
    
    /** Attribute expiry year. */
    private Integer expiryYear;
    
    /** Attribute ValidFromMonth. */
    private Integer validFromMonth;
    
    /** Attribute ValidFromYear. */
    private Integer validFromYear;
    
    /** Attribute Issue Number. */
    private Long issueNumber;
    
    /** private CardToken. */
    private String cardToken;
    
    /** Card Holder's Name. */
    private String cardHolderName;

    /** Used from date time. */
    private Timestamp usedFromDateTime;

    /** Used to date time. */
    private Timestamp usedToDateTime;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the person.
     * 
     * @return the personId
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    @Audited
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person.
     * 
     * @param personId the personId to set
     */
    public void setPerson(Person personId) {
        this.person = personId;
    }

    /**
     * Gets the card provider.
     * 
     * @return the cardProvider
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CARD_PROVIDER_CODE")
    @Audited
    public CardProvider getCardProvider() {
        return cardProvider;
    }

    /**
     * Sets the card provider.
     * 
     * @param cardProvider the cardProvider to set
     */
    public void setCardProvider(CardProvider cardProvider) {
        this.cardProvider = cardProvider;
    }

    /**
     * Gets the bin number.
     * 
     * @return the binNumber
     */
    @Column(name = "BIN_NUMBER")
    @Audited
    public String getBinNumber() {
        return binNumber;
    }

    /**
     * Sets the bin number.
     * 
     * @param binNumber the binNumber to set
     */
    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    /**
     * Gets the maskedcard number.
     * 
     * @return the maskedcardNumber
     */
    @Column(name = "MASKED_CARD_NUMBER")
    @Audited
    public String getMaskedcardNumber() {
        return maskedcardNumber;
    }

    /**
     * Sets the maskedcard number.
     * 
     * @param maskedcardNumber the maskedcardNumber to set
     */
    public void setMaskedcardNumber(String maskedcardNumber) {
        this.maskedcardNumber = maskedcardNumber;
    }

    /**
     * Gets the card name.
     * 
     * @return the cardName
     */
    @Column(name = "CARD_NAME")
    @Audited
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the card name.
     * 
     * @param cardName the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * Gets the expiry month.
     * 
     * @return the expiryMonth
     */
    @Column(name = "EXPIRY_MONTH")
    @Audited
    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    /**
     * Sets the expiry month.
     * 
     * @param expiryMonth the expiryMonth to set
     */
    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    /**
     * Gets the expiry year.
     * 
     * @return the expiryYear
     */
    @Column(name = "EXPIRY_YEAR")
    @Audited
    public Integer getExpiryYear() {
        return expiryYear;
    }

    /**
     * Sets the expiry year.
     * 
     * @param expiryYear the expiryYear to set
     */
    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    /**
     * Gets the valid from month.
     * 
     * @return the validFromMonth
     */
    @Column(name = "VALID_FROM_MONTH")
    @Audited
    public Integer getValidFromMonth() {
        return validFromMonth;
    }

    /**
     * Sets the valid from month.
     * 
     * @param validFromMonth the validFromMonth to set
     */
    public void setValidFromMonth(Integer validFromMonth) {
        this.validFromMonth = validFromMonth;
    }

    /**
     * Gets the valid from year.
     * 
     * @return the validFromYear
     */
    @Column(name = "VALID_FROM_YEAR")
    @Audited
    public Integer getValidFromYear() {
        return validFromYear;
    }

    /**
     * Sets the valid from year.
     * 
     * @param validFromYear the validFromYear to set
     */
    public void setValidFromYear(Integer validFromYear) {
        this.validFromYear = validFromYear;
    }

    /**
     * Gets the issue number.
     * 
     * @return the issueNumber
     */
    @Column(name = "ISSUE_NUMBER")
    @Audited
    public Long getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the issue number.
     * 
     * @param issueNumber the issueNumber to set
     */
    public void setIssueNumber(Long issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     * Gets the card token.
     * 
     * @return the cardToken
     */
    @Column(name = "CARD_TOKEN")
    @Audited
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Sets the card token.
     * 
     * @param cardToken the cardToken to set
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    /**
     * Gets the card holder name.
     * 
     * @return the cardHolderName
     */
    @Column(name = "CARD_HOLDER_NAME")
    @Audited
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the card holder name.
     * 
     * @param cardHolderName the cardHolderName to set
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * Gets the used from date time.
     * 
     * @return the usedFromDateTime
     */
    @Column(name = "USED_FROM_DATETIME")
    @NotAudited
    public Timestamp getUsedFromDateTime() {
        return usedFromDateTime;
    }

    /**
     * Sets the used from date time.
     * 
     * @param usedFromDateTime the usedFromDateTime to set
     */
    public void setUsedFromDateTime(Timestamp usedFromDateTime) {
        this.usedFromDateTime = usedFromDateTime;
    }

    /**
     * Gets the used to date time.
     * 
     * @return the usedToDateTime
     */
    @Column(name = "USED_TO_DATETIME")
    @NotAudited
    public Timestamp getUsedToDateTime() {
        return usedToDateTime;
    }

    /**
     * Sets the used to date time.
     * 
     * @param usedToDateTime the usedToDateTime to set
     */
    public void setUsedToDateTime(Timestamp usedToDateTime) {
        this.usedToDateTime = usedToDateTime;
    }
   


    /**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     * 
     * @return a <code>String</code> representation of this object.
     */
    @Override
    public String toString() {

        final String tab = "    ";
        return new StringBuilder("PaymentCard ( ").append("id = ").append(id)
                .append(tab).append("cardProvider = ").append(cardProvider)
                .append(tab).append("binNumber = ").append(binNumber).append(
                        tab).append("maskedcardNumber = ").append(
                        maskedcardNumber).append(tab).append("cardName = ")
                .append(cardName).append(tab).append("expiryMonth = ").append(
                        expiryMonth).append(tab).append("expiryYear = ")
                .append(expiryYear).append(tab).append("validFromMonth = ")
                .append(validFromMonth).append(tab).append("validFromYear = ")
                .append(validFromYear).append(tab).append("issueNumber = ")
                .append(issueNumber).append(tab).append("cardToken = ").append(
                        cardToken).append(tab).append("cardHolderName = ")
                .append(cardHolderName).append(tab).append(super.toString())
                .append(" )").toString();
    }
}
