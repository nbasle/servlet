package com.yaps.petstore.server.domain;


/**
 * This class encapsulates all the data for a credit card.
 *
 * @see com.yaps.petstore.server.domain.customer.Customer
 * @see com.yaps.petstore.server.domain.order.Order
 */
public final class CreditCard extends DomainObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _creditCardNumber;
    private String _creditCardType;
    private String _creditCardExpiryDate;

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getCreditCardNumber() {
        return _creditCardNumber;
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        _creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return _creditCardType;
    }

    public void setCreditCardType(final String creditCardType) {
        _creditCardType = creditCardType;
    }

    public String getCreditCardExpiryDate() {
        return _creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        _creditCardExpiryDate = creditCardExpiryDate;
    }
}
