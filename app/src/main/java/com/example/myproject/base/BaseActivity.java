package com.example.myproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.myproject.inter.IBase;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BaseContent.BasePresenter> extends AppCompatActivity implements IBase,BaseContent.BaseView {
    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        inject();
        if(mPresenter!=null){
            mPresenter.attachView(this);

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }




}
