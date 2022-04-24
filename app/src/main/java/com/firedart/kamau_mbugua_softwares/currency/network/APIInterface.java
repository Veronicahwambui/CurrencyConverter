package com.firedart.kamau_mbugua_softwares.currency.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIInterface {
    @GET("latest/{currency}")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);
}
