package com.example.myrikao0517.net.AddCart;

import com.example.myrikao0517.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddCartService {
    @FormUrlEncoded
    @POST("product/addCart")
    Observable<BaseBean> addCart(@Field("uid") String uid, @Field("pid") String pid, @Field("token") String token);

}
