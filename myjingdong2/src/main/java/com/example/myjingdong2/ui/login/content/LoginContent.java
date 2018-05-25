package com.example.myjingdong2.ui.login.content;

import com.example.myjingdong2.bean.UserBean;
import com.example.myjingdong2.ui.base.BaseContent;

public interface LoginContent {
    interface View extends BaseContent.BaseView{

        void loginSuccess(UserBean userBean);

    }

    interface  Presenter extends BaseContent.BasePresenter<View>{

        void login(String mobile,String password);



    }
}
