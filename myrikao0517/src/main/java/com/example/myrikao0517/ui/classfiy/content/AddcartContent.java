package com.example.myrikao0517.ui.classfiy.content;

import com.example.myrikao0517.base.BaseContent;

public interface AddcartContent {
    interface View extends BaseContent.BaseView{
        void onSuccess(String str);



    }

    interface  Presneter extends BaseContent.BasePresenter<View>{
        void addCart(String uid,String pid,String token);



    }
}
