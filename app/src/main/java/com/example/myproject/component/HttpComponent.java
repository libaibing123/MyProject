package com.example.myproject.component;

import com.example.myproject.module.HttpModule;
import com.example.myproject.ui.LoginActivity;

import dagger.Component;

@Component(modules = HttpModule.class)

public interface HttpComponent {
    void inject(LoginActivity loginActivity);
}
