package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiClient {

    @Headers("user-agent: ignis-android")

    @GET("characters/?api_key=9aa1dc67801a2cdc8460790837f94b73057ce351&filter=id:1699&format=json")
    Call<ApiResponse> getCharacter();
}
