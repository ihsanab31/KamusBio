package com.skripsi.dija.kamusbio.api;


public class BaseURL {

    public static EndPoint getAPIService() {
        return ApiClient.getClient().create(EndPoint.class);
    }
}
