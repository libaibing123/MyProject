package com.example.myrikao0517.base;



public interface BaseContent {
    interface BaseView{
        void showLoading();
        void dismissLoading();


    }
    interface BasePresenter<T extends BaseView>{
        void attachView(T view);
        void dettachView();


    }
}
