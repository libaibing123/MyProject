package com.example.myrikao0517.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrikao0517.inter.IBase;

import javax.inject.Inject;

public abstract class BaseFragment<T extends BaseContent.BasePresenter> extends Fragment implements IBase,BaseContent.BaseView  {
    @Inject
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if(mPresenter!=null){
            mPresenter.attachView(this);


        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter!=null){
            mPresenter.dettachView();


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), null);
        initView(view);

        return view;


    }

    @Override
    public void showLoading() {


    }

    @Override
    public void dismissLoading() {


    }
}
