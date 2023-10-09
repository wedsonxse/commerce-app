package com.commerce.commerce.adapters;

public interface Dispatcher {
    public void sendNotification(String destinationEmail, String desinationName, Double transferedTotal) throws Exception;
}
