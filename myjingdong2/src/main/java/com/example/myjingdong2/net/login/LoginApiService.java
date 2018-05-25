package com.example.myjingdong2.net.login;

import com.example.myjingdong2.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApiService {
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserBean> login(@Field("mobile") String mobile,@Field("password") String password);
}
