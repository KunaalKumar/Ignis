package com.kunaalkumar.ignis.network;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @Headers("user-agent: ignis-android")

    // Searches for character via given name
    @GET("characters/")
    Call<ApiResponse> searchCharacters(@Query("api_key") String apiKey,
                                       @Query("filter") String filter,
                                       @Query("format") String format);

    // TODO: Test
    // Gets character via given character id
    @GET("character/")
    Call<ApiResponse> getCharacter(@Path("character_id") String characterId,
                                   @Query("api_key") String apiKey,
                                   @Query("formay") String format);
}
