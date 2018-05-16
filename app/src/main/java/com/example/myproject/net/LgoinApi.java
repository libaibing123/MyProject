package com.example.myproject.net;

import android.widget.Button;

import com.example.myproject.bean.UserBean;

import io.reactivex.Observable;

public class LgoinApi {

    private static  LgoinApi lgoinApi;
    private LgoinApiService lgoinApiService;
    private    LgoinApi (LgoinApiService lgoinApiService){
        this.lgoinApiService=lgoinApiService;



    }

    public static LgoinApi getLgoinApi(LgoinApiService lgoinApiService){
        if(lgoinApi==null){
            lgoinApi=new LgoinApi(lgoinApiService);

        }
      return lgoinApi;
    }

    public Observable<UserBean> login(String mobile,String password){

        return lgoinApiService.login(mobile,password);

    };

}
