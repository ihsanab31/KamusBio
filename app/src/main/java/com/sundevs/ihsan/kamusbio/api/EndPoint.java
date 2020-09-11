package com.sundevs.ihsan.kamusbio.api;


import com.sundevs.ihsan.kamusbio.model.response.KamuBioResponse;
import com.sundevs.ihsan.kamusbio.model.response.KamusResponse;
import com.sundevs.ihsan.kamusbio.model.response.LatinResponse;
import com.sundevs.ihsan.kamusbio.model.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
