package com.mirea.iri.kt.dnschecker;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Auth {
    @FormUrlEncoded
    @POST("/coursework/login.php")
    Call<AuthResponse> authenticate(@Field("lgn") String login, @Field("pwd") String password, @Field("g") String group);
}
