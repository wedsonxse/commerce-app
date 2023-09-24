package com.commerce.commerce.adapters;

public class TransactionManager implements Authorizer {
    public boolean authorizeTransaction() {
        return false;
    }
}
