package com.commerce.commerce.requests;

import lombok.Data;

@Data
public class TransactionPostRequest {
    Double credits;
    Long payer;
    Long payee;

    public Double getCredits() {
        return credits;
    }
}
