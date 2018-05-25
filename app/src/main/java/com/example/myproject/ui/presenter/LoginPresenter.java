package com.example.myproject.ui.presenter;

import com.example.myproject.base.BasePresenter;
import com.example.myproject.bean.UserBean;
import com.example.myproject.net.LgoinApi;
import com.example.myproject.ui.content.LoginContent;



import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContent.View> implements LoginContent.Presenter {
    private LgoinApi lgoinApi;
    @Inject
    public LoginPresenter(LgoinApi lgoinApi){
        this.lgoinApi=lgoinApi;


    }
    @Override
    public void login(String mobile, String password) {
        lgoinApi.login(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        mView.getSuccess(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
