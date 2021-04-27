package com.skripsi.dija.kamusbio.api;


import com.skripsi.dija.kamusbio.model.response.KamuBioResponse;
import com.skripsi.dija.kamusbio.model.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 23, October, 2018
 * ------------------------------
 * This class for  api response
 */
public interface EndPoint {

    @GET("listTumbuhan")
    Call<KamuBioResponse> getKamus(@Header("security") String security);

    @FormUrlEncoded
    @POST("input_user.php")
    Call<UserResponse> inputUser(@Field("nama") String nama,
                                 @Field("token") String token,
                                 @Field("nomor") String Nomor

    );

    @GET("listTumbuhan")
    Call<KamuBioResponse> getLatin(@Header("security") String security);
}
