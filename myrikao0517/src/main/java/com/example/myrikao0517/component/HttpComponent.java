package com.example.myrikao0517.component;

import android.app.ListActivity;

import com.example.myrikao0517.module.HttpModule;
import com.example.myrikao0517.ui.classfiy.ClassifyFragment;
import com.example.myrikao0517.ui.classfiy.ListDetailsActivity;
import com.example.myrikao0517.ui.homepage.HomePageFragment;
import com.example.myrikao0517.ui.login.LoginActivity;

import dagger.Component;


@Component(modules = HttpModule.class)
public interface HttpComponent {

    void inject(LoginActivity loginActivity);

    void inject(HomePageFragment homePageFragment);

    void inject(ClassifyFragment classifyFragment);

    void inject(ListActivity listActivity);

    //void inject(ListDetailsActivity listDetailsActivity);

    //void inject(ShopCartActivity shopCartActivity);

   // void inject(MakeSureOrderActivity makeSureOrderActivity);




}
