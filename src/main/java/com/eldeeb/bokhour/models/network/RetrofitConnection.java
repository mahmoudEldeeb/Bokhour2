package com.eldeeb.bokhour.models.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

    final static  String RootUrl="http://shmo5-perfume.com/index/";
    //final static String RootUrl="http://192.168.1.4/franchise/api/";
    private static Retrofit getInstance(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout

                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES); // read timeout

        OkHttpClient okHttpClient = builder.build();

        return new Retrofit.Builder()
                .baseUrl(RootUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static  NetworkData getNetworkConnection(){
        return getInstance().create(NetworkData.class);
    }
}