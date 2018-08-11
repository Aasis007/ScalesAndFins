package com.example.aasis.scalesandfins.Utls;

import com.example.aasis.scalesandfins.Model.User;
import com.example.aasis.scalesandfins.Retrofit.RetrofitClient;
import com.example.aasis.scalesandfins.Retrofit.ScalesAPI;

import retrofit2.Retrofit;

public class Common {
    private static final String BASE_URL ="http://192.168.0.105/ScalesAndFins/";

    public static User currentuUser = null;

    public static ScalesAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(ScalesAPI.class);

    }
}
