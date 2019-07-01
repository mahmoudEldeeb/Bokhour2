package com.eldeeb.bokhour.models.network;

import com.eldeeb.bokhour.models.dataModels.CategorysResults;
import com.eldeeb.bokhour.models.dataModels.ProductsResults;
import com.eldeeb.bokhour.models.dataModels.Result;
import com.eldeeb.bokhour.models.dataModels.Results;
import com.eldeeb.bokhour.models.dataModels.UserInfo;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkData {

    @POST("register-app.php")
    @FormUrlEncoded
    Single<Results> register(@Field("Lang") String Lang, @Field("FirstName") String FirstName,
                             @Field("LastName") String LastName, @Field("cpwd") String cpwd,
                             @Field("cpwd2") String cpwd2, @Field("cemail") String cemail,
                             @Field("Mobile") String Mobile, @Field("cgender") String cgender);


    @POST("app.php")
    @FormUrlEncoded
    Single<Result> saveProfile(@Query("lang") String Lang, @Query("opt") String opt,
                               @Query("cid") String cid,
                               @Query("apikey") String apikey,
                               @Field("cfirstname") String cfirstname,
                               @Field("clastname") String clastname, @Field("user_pwd") String user_pwd,
                               @Field("user_pwd2") String user_pwd2, @Field("cemail") String cemail,
                               @Field("cmobile") String cmobile, @Field("cgender") String cgender,
                               @Field("ccountry") String ccountry , @Field("ccity") String ccity ,
                               @Field("caddress") String caddress , @Field("cccno") String cccno ,
                               @Field("cseccode") String cseccode , @Field("expireMM") int expireMM ,
                               @Field("expireYY") int expireYY
                                );


    @POST("login-app.php")
    @FormUrlEncoded
    Single<Results> login(@Field("Lang") String Lang, @Field("email") String email,
                             @Field("password") String password);

    @GET("app.php")
    Single<CategorysResults> getCategory(@Query("Lang") String Lang, @Query("opt") String opt,
                                         @Query("apikey") String apikey);


    @GET("app.php")
    Single<ProductsResults> getLastProducts(@Query("Lang") String Lang, @Query("opt") String opt,
                                            @Query("apikey") String apikey);


    @GET("app.php")
    Single<ProductsResults> getProductsOfCategory(@Query("Lang") String Lang, @Query("opt") String opt,
                                            @Query("apikey") String apikey,
                                                  @Query("categories") String categories,@Query("categories_sub") String categories_sub
    );

    @GET("app.php")
    Single<Results> getprofile(@Query("Lang") String Lang, @Query("opt") String opt,
                                @Query("apikey") String apikey,
                                @Query("ccode") String ccode, @Query("cid") String cid);



}
