package com.sundevs.ihsan.kamusbio.api;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 23, October, 2018
 * ------------------------------
 * This class for
 */
public class BaseURL {

    private static final String BASE_URL_API = "https://projectmantaps.000webhostapp.com/";

    public static EndPoint getAPIService() {
        return ApiClient.getClient(BASE_URL_API).create(EndPoint.class);
    }
}
