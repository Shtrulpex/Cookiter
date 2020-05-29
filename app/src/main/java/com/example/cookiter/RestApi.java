package com.example.cookiter;

import android.content.Intent;

import com.example.cookiter.models.ProductsModel;
import com.example.cookiter.models.RecipeModel;
import com.example.cookiter.models.TrueFalseModel;
import com.example.cookiter.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RestApi {
    @POST("/user/create")
    Call<TrueFalseModel> create(@Body UserModel user);

    @GET("/user/getUserAccessbyLog")
    Call<TrueFalseModel> getAccessByLog(@Query("login") String login, @Query("password") int password );

    @GET("/user/getUserAccessbyEmail")
    Call<TrueFalseModel> getAccessByEmail(@Query("email") String email, @Query("password") int password );

    @GET("/pr/getAll")
    Call<List<ProductsModel>> getAllProducts();

    @GET("/user/getUserByEmail")
    Call<UserModel> getUserByEmail(@Query("email") String email);

    @GET("/user/getUserByLog")
    Call<UserModel> getUserByLog(@Query("login") String login);

    @POST("/rec/create")
    Call<RecipeModel> createRecipe(@Body RecipeModel recipes1);

    @PUT("/pr/update")
    Call<TrueFalseModel> updateProduct(@Query("id") Integer id, @Query("recipeId") Integer recipeId);

    @GET("/rec/getAll")
    Call<List<RecipeModel>> getAllRecipes();

    @GET("/pr/getById")
    Call<String[]> getPrById(@Query("id")Integer id[]);

    @GET("/rec/getByLog")
    Call<List<RecipeModel>> getByLog(@Query("login")String login);

}
