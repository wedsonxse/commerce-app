package com.commerce.commerce.adapters;

import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service("Auth")
public class TransactionManager implements Authorizer {
    private static final String AUTH_ENDPOINT = "https://run.mocky.io/v3/23bbb9f9-30a6-4ca6-852c-5dbf9c3033e1";
    public boolean authorizeTransaction() throws Exception {

        try{
            URL obj = new URL(TransactionManager.AUTH_ENDPOINT);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            return responseCode == 200;
        }catch(Exception e){
            throw new Exception("Ocorreu um problema durante a autorização da transação: " + e.getMessage());
        }
    }
}
