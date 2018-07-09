package com.example.aasis.scalesandfins.Retrofit;

import com.example.aasis.scalesandfins.Model.Banner;
import com.example.aasis.scalesandfins.Model.CheckUserResponse;
import com.example.aasis.scalesandfins.Model.Newarrival;
import com.example.aasis.scalesandfins.Model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ScalesAPI {
    @POST("checkuser.php")
    @FormUrlEncoded
    Call<CheckUserResponse> checkUserExists(@Field("phone") String phone);

    @POST("register.php")
    @FormUrlEncoded
    Call<User> registerNewUser(@Field("phone") String phone,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("birthdate") String birthdate);

    @POST("getuser.php")
    @FormUrlEncoded
    Call<User> getUSerInfo(@Field("phone") String phone);


    @GET("getbanner.php")
    io.reactivex.Observable<List<Banner>> getBanners();

    @GET("getnew.php")
    io.reactivex.Observable<List<Newarrival>> getnewarrival();
}
