package com.example.myproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.base.BaseActivity;
import com.example.myproject.bean.UserBean;
import com.example.myproject.ui.content.LoginContent;
import com.example.myproject.ui.presenter.LoginPresenter;
import com.example.myproject.utils.SharedPreferencesUtils;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContent.View, View.OnClickListener {

    private EditText mMobile;
    private EditText mPassword;
    /**
     * 登录
     */
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        initView();
    }
    @Override
    public void inject() {

databaseList();

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void getSuccess(UserBean userBean) {
        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(LoginActivity.this,"uid",userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"name",userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"iconUrl",userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",userBean.getData().getToken() + "");
        LoginActivity.this.finish();



    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private void initView() {
        mMobile = (EditText) findViewById(R.id.mobile);
        mPassword = (EditText) findViewById(R.id.password);
        mBtLogin = (Button) findViewById(R.id.btLogin);
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btLogin:
                String mobile = mMobile.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.login(mobile,password);
                break;
        }
    }
}
