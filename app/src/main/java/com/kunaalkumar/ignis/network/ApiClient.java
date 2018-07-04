package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @Headers("user-agent: ignis-android")

// characters/?api_key=9aa1dc67801a2cdc8460790837f94b73057ce351&filter=name:Batman&format=json

    // Searches for character via given name
    @GET("characters/")
    Call<ApiResponse> searchCharacters(@Query("api_key") String apiKey, @Query("filter") String filter, @Query("format") String format);

    // Gets character via given character id
    @GET("character/4005-{character_id}/?api_key=9aa1dc67801a2cdc8460790837f94b73057ce351&format=json")
    Call<ApiResponse> getCharacter(@Path("character_id") String characterId);
}
