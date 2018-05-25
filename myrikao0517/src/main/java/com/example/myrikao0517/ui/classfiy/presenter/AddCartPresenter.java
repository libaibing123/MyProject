package com.example.myrikao0517.ui.classfiy.presenter;

import com.example.myrikao0517.base.BasePresenter;
import com.example.myrikao0517.bean.BaseBean;
import com.example.myrikao0517.net.AddCart.AddCartApi;
import com.example.myrikao0517.ui.classfiy.content.AddcartContent;
import com.example.myrikao0517.ui.homepage.content.HomeContent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AddCartPresenter extends BasePresenter<AddcartContent.View> implements AddcartContent.Presneter {
    private AddCartApi addCartApi;

    private AddCartPresenter(AddCartApi addCartApi){
        this.addCartApi=addCartApi;


    }

    @Override
    public void addCart(String uid, String pid, String token) {

        addCartApi.getCatagory(uid,pid,token)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
               if(mView!=null){
                   mView.onSuccess(s);

               }
            }
        });


    }
}
