package com.example.myrikao0517.ui.login.contract;


import com.example.myrikao0517.base.BaseContent;
import com.example.myrikao0517.bean.UserBean;

public interface LoginContract {
    interface View extends BaseContent.BaseView {
        void loginSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContent.BasePresenter<View> {
        void login(String mobile, String password);
    }
}
