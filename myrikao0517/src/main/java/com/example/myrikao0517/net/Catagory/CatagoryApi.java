package com.example.myrikao0517.net.Catagory;

import com.example.myrikao0517.bean.CatagoryBean;
import com.example.myrikao0517.net.CatagoryApiService;

import io.reactivex.Observable;

public class CatagoryApi {

    private  static CatagoryApi catagoryApi;
    private CatagoryApiService catagoryApiService;

    public CatagoryApi(CatagoryApiService catagoryApiService) {
        this.catagoryApiService = catagoryApiService;
    }
    public static CatagoryApi getCatagoryApi(CatagoryApiService catagoryApiService){

        if(catagoryApi==null){
            catagoryApi=new CatagoryApi(catagoryApiService);


        }
        return catagoryApi;

    }

    public Observable<CatagoryBean> getCatagory(){

        return catagoryApiService.getCatagory();


    }
}
