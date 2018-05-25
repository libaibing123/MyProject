package com.example.myrikao0517.net.ProductCatagory;

import com.example.myrikao0517.bean.ProductCatagoryBean;

import io.reactivex.Observable;

public class ProductCatagoryApi {
    private static ProductCatagoryApi productCatagoryApi;
    private ProductCatagoryService productCatagoryService;

    public ProductCatagoryApi(ProductCatagoryService productCatagoryService) {
        this.productCatagoryService = productCatagoryService;
    }

    public static ProductCatagoryApi getProductCatagoryApi(ProductCatagoryService productCatagoryService){
        if(productCatagoryApi==null){

            productCatagoryApi=new ProductCatagoryApi(productCatagoryService);

        }
        return productCatagoryApi;

    }

    public Observable<ProductCatagoryBean> getProductCatagory(String cid){
        return  productCatagoryService.getProductCatagory(cid);


    }
}
