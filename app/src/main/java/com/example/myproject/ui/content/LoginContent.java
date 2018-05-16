package com.example.myproject.ui.content;

import com.example.myproject.base.BaseContent;
import com.example.myproject.bean.UserBean;

public interface LoginContent {
    interface View extends BaseContent.BaseView{

        void getSuccess(UserBean userBean);

    }

    interface Presenter extends BaseContent.BasePresenter<View>{
        void login(String mobile,String password);



    }
}
