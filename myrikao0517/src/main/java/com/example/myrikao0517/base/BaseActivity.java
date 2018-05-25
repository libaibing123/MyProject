package com.example.myrikao0517.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myrikao0517.R;
import com.example.myrikao0517.inter.IBase;
import com.example.myrikao0517.utils.SharedPreferencesUtils;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BaseContent.BasePresenter> extends AppCompatActivity implements BaseContent.BaseView,IBase{
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
    public void initView(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.dettachView();


        }
    }



    @Override
    public void showLoading() {


    }

    @Override
    public void dismissLoading() {

    }
    protected String getUid(){
        return (String) SharedPreferencesUtils.getParam(this,"uid","");

    }
    protected String getToken() {
        return (String) SharedPreferencesUtils.getParam(this, "token", "");
    }

    protected void toast(String str){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }
}
