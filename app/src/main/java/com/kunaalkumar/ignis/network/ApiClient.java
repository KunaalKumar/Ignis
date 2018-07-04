package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiClient {

    @Headers("user-agent: ignis-android")


    // Searches for character via given name
    @GET("characters/?api_key=9aa1dc67801a2cdc8460790837f94b73057ce351&filter=name:{name}&format=json")
    Call<ApiResponse> searchCharacters(@Path("name") String name);

    // Gets character via given character id
    @GET("character/4005-{character_id}/?api_key=9aa1dc67801a2cdc8460790837f94b73057ce351&format=json")
    Call<ApiResponse> getCharacter(@Path("character_id") String characterId);
}
