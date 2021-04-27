package com.skripsi.dija.kamusbio.api;

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

    public static EndPoint getAPIService() {
        return ApiClient.getClient().create(EndPoint.class);
    }
}
