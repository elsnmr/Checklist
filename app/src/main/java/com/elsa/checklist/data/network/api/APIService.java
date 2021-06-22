package com.elsa.checklist.data.network.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @POST("login")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> login(
            @Body Map<String, Object> data
    );

    @GET("checklist")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> checklist(
            @Header("Authorization") String authorization
    );
}
