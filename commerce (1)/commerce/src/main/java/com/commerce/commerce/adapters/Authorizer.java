package com.commerce.commerce.adapters;
import org.springframework.stereotype.Component;

@Component
public interface Authorizer {
    public boolean authorizeTransaction() throws Exception;
}
