package com.example.myproject.base;

public interface BaseContent {
    interface BasePresenter<T extends BaseView >{

        void attachView(T view);
        void dettachView();



    }

    interface BaseView{

      void   showLoading();
      void dismissLoading();



    }
}
