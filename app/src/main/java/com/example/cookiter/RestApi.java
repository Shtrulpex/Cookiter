package com.example.cookiter;

import com.example.cookiter.models.TrueFalseModel;
import com.example.cookiter.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @POST("/user/create")
    Call<TrueFalseModel> create(@Body UserModel user);
}
