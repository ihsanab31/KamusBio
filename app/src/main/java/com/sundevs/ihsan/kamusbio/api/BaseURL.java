package com.sundevs.ihsan.kamusbio.api;

import com.sundevs.ihsan.kamusbio.utils.Constant;

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
        return ApiClient.getClient(Constant.BASE_URL_API).create(EndPoint.class);
    }
}
