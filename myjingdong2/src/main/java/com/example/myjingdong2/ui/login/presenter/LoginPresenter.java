package com.example.myjingdong2.ui.login.presenter;

import com.example.myjingdong2.net.login.LoginApi;
import com.example.myjingdong2.ui.base.BasePresenter;
import com.example.myjingdong2.ui.login.content.LoginContent;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContent.View> implements LoginContent.Presenter{
    private LoginApi loginApi;

    @Inject
   public LoginPresenter(LoginApi loginApi){
        this.loginApi=loginApi;


    }
    @Override
    public void login(String mobile, String password) {


    }
}
