package com.example.myrikao0517.ui.classfiy.presenter;

import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.base.BasePresenter;
import com.example.myrikao0517.bean.ProductsBean;
import com.example.myrikao0517.net.List.ListApi;
import com.example.myrikao0517.ui.classfiy.content.ListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter extends BasePresenter<ListContract.View> implements ListContract.Presenter {
    private ListApi listApi;

    @Inject
    public ListPresenter(ListApi listApi){
        this.listApi=listApi;


    }


    @Override
    public void getProducts(String pscid) {

        listApi.getProduct(pscid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ProductsBean, List<ProductsBean.DataBean>>() {
                    @Override
                    public List<ProductsBean.DataBean> apply(ProductsBean productsBean) throws Exception {
                        return productsBean.getData();
                    }
                }).subscribe(new Consumer<List<ProductsBean.DataBean>>() {
            @Override
            public void accept(List<ProductsBean.DataBean> dataBeans) throws Exception {
           if(mView!=null){
               mView.onSuccess(dataBeans);

           }
            }
        });

    }


}
