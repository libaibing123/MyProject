package com.example.myrikao0517.module;

import com.example.myrikao0517.net.Ad.AdApi;
import com.example.myrikao0517.net.Ad.AdApiService;
import com.example.myrikao0517.net.AddCart.AddCartApi;
import com.example.myrikao0517.net.AddCart.AddCartService;
import com.example.myrikao0517.net.Api;
import com.example.myrikao0517.net.CatagoryApi;
import com.example.myrikao0517.net.CatagoryApiService;
import com.example.myrikao0517.net.login.LoginApi;
import com.example.myrikao0517.net.login.LoginApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){

        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS);

    }


    @Provides
    LoginApi provideLoginApi(OkHttpClient.Builder builder){

        LoginApiService loginApiService = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(LoginApiService.class);
        return LoginApi.getLoginApi(loginApiService);



    }

    @Provides
    AdApi provideAdApi(OkHttpClient.Builder builder){
        AdApiService adApiService = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()
                .create(AdApiService.class);
        return  AdApi.getAdApi(adApiService);



    }
    @Provides
    CatagoryApi provideCatagoryApi(OkHttpClient.Builder builder){

        CatagoryApiService catagoryApiService = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()
                .create(CatagoryApiService.class);
return CatagoryApi.getCatagoryApi(catagoryApiService);
    }

    @Provides
    AddCartApi provideAddCartApi(OkHttpClient.Builder builder){
        AddCartService addCartService = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(AddCartService.class);
        return AddCartApi.getAddCartApi(addCartService);


    }



}
