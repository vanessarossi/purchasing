package com.purchasing.enumerator;

/**
 * @author vanessa
 */
public enum MeanPaymentEnum {
    Money("Dinheiro"),
    Check("Cheque"),
    BilletBanking("Boleto"),
    CreditCard("Cartão"),
    DepositAccount("Depósito em Conta"),
    RPA("R.P.A");

    private String description;

    MeanPaymentEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
